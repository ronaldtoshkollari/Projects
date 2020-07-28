#include <iostream>
#include <string>
#include <locale>
#include <fstream>
#include "Image.h"
#include "Color.h"
#include "ppm/ppm.h"

using namespace std;
using namespace imaging;

//constructors and destructor

Image::Image() {
	width = 0;
	height = 0;
	buffer = nullptr;
}//end of default const

Image::Image(unsigned int width, unsigned int height)
	:width(width) ,height(height)
{}//end of constructor

Image::Image(unsigned int width, unsigned int height, const Color * data_ptr)
	: width(width), height(height) {
	*buffer = *data_ptr;
}//end of constructor

Image::Image(const Image &src)
	: buffer(src.buffer)
{}

Image::~Image()
{
	delete buffer;
	cout << "Destructing Image\n";
}






//methods

void imaging::Image::setData(const Color *& data_ptr)
{
	if (sizeof data_ptr < 12) {
		cerr << "Color is empty";
	}
	else {
		for (unsigned int i = 0; i < width; i++) {
			for (unsigned int j = 0; j < height; j++) {
				buffer[i*j] = data_ptr[i*j];
			}//end for j
		}//end for i
	}//end if

}//end of setData

Color* Image::getRawDataPtr() {
	return buffer;
}//end of getRawDataPtr()

Color Image::getPixel(unsigned int x, unsigned int y) const {
	//TODO
	if (x >= getWidth() || y >= getHeight()) {
		return Color();
	}
	else {
		
		return Color(buffer[x + y].r, buffer[x + y + 1].g, buffer[x + y + 2].b);
	}
}//end of getPixel

void Image::setPixel(unsigned int x, unsigned int y, Color & value) {

	if (x >= getWidth() || y >= getHeight()) {
		cerr << "Width of Height out of bounds." << endl;
	}
	else {
		buffer[x+y].r = value.r;
		buffer[(x+y)+1].g = value.g;
		buffer[(x+y) + 2].b = value.b;

	}//end if


}//end of set pixel


bool Image::load(const string &fileName, const string &format) {
	bool match = true;
	char bullet = '.';
	bool foundBullet = false;

	string givenFileFormat = "";

	//checking if the formats match each other
	for (unsigned int i = 0; i < fileName.size(); i++) {

		if (fileName.at(i) == bullet) {
			foundBullet = true;
			continue;
		}//end if

		if (foundBullet) {
			givenFileFormat += fileName.at(i);
		}//end 

	}//end for

	for (unsigned int i = 0; i < givenFileFormat.size(); i++) {
		if (tolower(givenFileFormat[i]) != tolower(format[i]) ) {
			cout << "Formats does not match. \n" << endl;
			match = false;
		}
	}//end for
	/*
	 *   end of checking formats
	 */




	if (match) {
		string tempString = fileName;
		const char *cstr = tempString.c_str(); //making string into a c_string
		int tempW = 0, tempH = 0;
		const float* b = ReadPPM(cstr, &tempW, &tempH);
		
		if (b == nullptr) {
			cerr << "Could not load the image.";
			return false;
		}
		
		width = tempW;
		height = tempH;

		this->buffer = new Color[width*height];
		int j = 0;
		for (int i = 0; i < width*height; ++i) {
			this->buffer[i].r = b[j];
			this->buffer[i].g = b[j + 1];
			this->buffer[i].b = b[j + 2];
			j += 3;
		}//end for
		

	}//end if



	return true;


}

bool Image::save(const string &filename,const string & format) {
	bool match = true;
	char bullet = '.';
	bool foundBullet = false;
	string name = "";

	string givenFileFormat = "";

	//checking if the formats match each other
	for (unsigned int i = 0; i < filename.size(); i++) {

		if (filename.at(i) == bullet) {
			foundBullet = true;
			continue;
		}//end if

		if (foundBullet) {
			givenFileFormat += filename.at(i);
		}
		else {
			name += filename.at(i);
		}

	}//end for

	for (unsigned int i = 0; i < givenFileFormat.size(); i++) {
		if (tolower(givenFileFormat[i]) != tolower(format[i])) {
			cout << "Formats does not match. \n" << endl;
			match = false;
		}
	}//end for
	/*
	 *   end of checking formats
	 */

	/*
	const Color * negative = getRawDataPtr();
	Color white = Color(1, 1, 1);
	Color orig;
	for (int i = 0; i < width; ++i) {
		for (int j = 0; j < height; ++j) {
			orig = getPixel(i, j);
			Color neg = white - orig;
			setPixel(i, j, neg);
		}//end for

	}//end for
	setData(negative);*/

	
	if (match) {
		//making Color* to  float *
		float * data = new float[3 * width*height];
		int size = 3 * width*height;
		int j = 0;
		for (int i = 0; i < width*height; ++i) {
			data[j] = 1.0 - this->buffer[i].r;
			data[j + 1] = 1.0 - this->buffer[i].g;
			data[j + 2] = 1.0 - this->buffer[i].b;
			j += 3;

		}//end for
		string iName = name + "_neg." + format;

		const char * iFormat = iName.c_str();


		WritePPM(data, width, height, iFormat);

		return true;
	}
	else {
		cerr << "Error on format \n" << endl;
		return false;
	}
}//end save

