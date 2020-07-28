#include "stdafx.h"
#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib>
#include "ppm.h"


using namespace imaging;
using namespace std;



float * imaging::ReadPPM(const char * filename, int * w, int * h)
{
	ifstream inFile;
	int size = 0;
	inFile.open(filename, ios::binary);
	if (inFile.fail()) {
		cerr << "Error Opening file.." << endl;
		inFile.close();
		exit(1);
	}//end if


	/*
	*
	*	READING HEADER OF PPM.	
	*
	*/
	string type = "";
	int width = 0;
	int height = 0;
	int max = 0;

	inFile >> type;
	if (strcmp(type.c_str(), "P6") != 0)
		cerr << "Invalid type.";
	inFile >> width >> height >> max;
	*w = width;
	*h = height;
	char garbage = inFile.peek();
	while (garbage == '\n' || garbage == ' ') {
		if (garbage == '\n') {
			inFile.ignore(256, '\n');
		}
		if (garbage == ' ') {
		
			inFile.ignore(256, ' ');
			//inFile.get();
			//inFile.tellg();
			//inFile.get();
			

			
		}
		garbage = inFile.peek();
	}


	/*
	
	
		READING BINARY FILE AS CHAR AND CONVERTING IT TO FLOAT
	
	
	
	*/



	char * pixels = new char[3 * width*height];
	inFile.read((char*)pixels, 3* width*height);
	float *board = new float[3 * width*height];

	for (int i = 0; i < 3 * width*height; ++i) {
		board[i] = pixels[i] / 255.f;
	}
	

	inFile.close();
	//WritePPM(board, width, height, "test.ppm");
	return board;
	//return nullptr
}

bool imaging::WritePPM(const float * data, int w, int h, const char * filename)
{
	ofstream outfile;
	outfile.open(filename, ios::out | ios::binary);
	if (outfile.fail()) {
		cerr << "Error Opening file.." << endl;
		exit(1);
	}//end if

	outfile << "P6\n" << w << "\n" << h << "\n" << "255\n";

	int size = 3 * w*h;
	char * pixels = new char[size];
	for (unsigned int i = 0; i < size; ++i) {

		pixels[i] = data[i] * 255;

	}//end for

	outfile.write(pixels, size);
	
	outfile.close();
	return true;
}//end of WritePPM
