#include <iostream>
#include <string>
#include <fstream>
#include "ppm/ppm.h"
#include "Image.h"
#include "Array.h"
#include "Vec3.h"



using namespace std;
using namespace imaging;
using namespace math;


bool math::Image::load(const string filename, const  string &format) {

	int  wi, he;
	string tempF = format;
	if (format.compare("ppm") != 0) {
		cerr << "Wrong format.It must be ppm";
		return false;
	}
	else {

		float *h = ReadPPM(filename.c_str(), &wi, &he);
		width = wi;
		height = he;
		if (width == 0 || height == 0) {
			cerr << "Width or Height is 0. ERROR reading file";
			return false;
		}

		for (unsigned int i = 0; i < width*height; i++) {
			Color C;
			C.x = h[(i * 3) + 0];
			C.y = h[(i * 3) + 1];
			C.z = h[(i * 3) + 2];
			//adds current color vector to data
			buffer.push_back(C);
		}//end fors
		return true;

	}//end if

}//end of load

bool math::Image::save(const string & filename, const string &format) {

	float *fBuff = new float[width*height * 3];

	unsigned int i;
	for (i = 0; i < getWidth()*getHeight(); i++) {
		*(fBuff + i * 3) = buffer[i].x;
		*(fBuff + i * 3 + 1) = buffer[i].y;
		*(fBuff + i * 3 + 2) = buffer[i].z;
	}

	return WritePPM(fBuff, getWidth(), getHeight(), filename.c_str());



}//end of save
