#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib>
#include <string>
#include "Image.h"
#include "ppm/ppm.h"

using namespace imaging;
using namespace std;

int main(int argc, char *argv[]) {

	Image h;
	if (argc < 2) {
		string file, format;
		cout << "Please enter the image you want to edit: ";
		cin >> file;
		cout << "Please enter the format of your file: ";
		cin >> format;
		h.load(file, format);
		cout << "WidthxHeight: " << h.getWidth() << "x" << h.getHeight() << endl;
		cout << "Please enter the format that you want for your photo(Supported PPM): ";
		cin >> format;
		h.save(file, format);
	}
	else {
		string file, format;
		h.load(argv[1], "ppm");
		cout << "WidthxHeight: " << h.getWidth() << "x" << h.getHeight() << endl;
		cout << "Please enter the format that you want for your photo(Supported PPM): ";
		cin >> format;
		h.save(file, format);
	}


	system("pause");
	return 0;
}