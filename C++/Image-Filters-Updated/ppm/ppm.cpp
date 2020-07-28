#include "stdafx.h"
#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib>
#include "ppm.h"

using namespace imaging;
using namespace std;

float * imaging::ReadPPM(const char * filename, int * w, int *h) {

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
	string line;
	int width = 0;
	int height = 0;
	int max = 0;
	
	inFile >> type;
	if (strcmp(type.c_str(), "P6") != 0) {
		cerr << "Invalid type.";
		return nullptr;
	}
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

	size = 3 * (*w) * (*h);
	unsigned char * pixels = new unsigned char[size];
	float * board = new float[size];

	inFile.read((char *)pixels, size);
	inFile.close();
	for (int i = 0; i < size; i++) {

		*(board + i) = (float)(*(pixels + i)) / (float)max;

	}//end for

	return board;


}//end of ReadPPM

bool imaging::WritePPM(const float * data, int w, int h, const char * filename) {

	ofstream outFile;
	outFile.open(filename, ios::binary);
	if (outFile.fail()) {
		cerr << "Error Opening file.." << endl;
		outFile.close();
		return false;
	}//end if

	int size = 3 * w*h;
	outFile << "P6\n" << w << "\n" << h << "\n" << "255\n";
	if (data == nullptr) {
		return false;
	}
	char *buf = new char[size];
	for (int i = 0; i < size; i++) {
		buf[i] = (char)(data[i] * 250.0);
	}
	outFile.write((char*)buf, size * sizeof(char));
	outFile.close();
	return true;

}//end of WritePPM