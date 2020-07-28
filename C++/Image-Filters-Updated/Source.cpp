#include <iostream>
#include "ppm/ppm.h"
#include "Image.h"
#include "ppm/ppm.h"
#include "Array.h"
#include "Vec3.h"
#include "FilterGamma.h"
#include "FilterLinear.h"
#include "FilterBlur.h"
#include <vector>

using namespace std;
using namespace imaging;
using namespace math;

int main(int argc, char* argv[]) {
	
	float ax, ay, az, cx, cy, cz, gp;
	Image I, O;
	FilterLinear FL;
	FilterGamma FG;
	if(argc < 4){
		cerr << "Syntax error\n" << "Usage: filter [FILTER_TYPE] [FILTER PARAMETERS] ... [IMAGE FILENAME]\n";
		
	}
	else {

		cout << "LOADING IAMGE...." << endl;
		I.load(argv[argc - 1], "ppm");
		cout << "IMAGE LOADED!" << endl;
		cout << "Making copy of the image..." << endl;
		string name = "filtered_" + string(argv[argc - 1]);
		I.save(name, "ppm");
		cout << "Copy made with name " << name << endl;
		O.load(name, "ppm");
		unsigned int i = 1;
		while (i < (argc - 1)) {

			if (strcmp(argv[i], "linear") == 0) {
				i++;
				cout << "Applying Linear Filter......." << endl;
				ax = atof(argv[i]);
				i++;
				ay = atof(argv[i]);
				i++;
				az = atof(argv[i]);
				i++;

				cx = atof(argv[i]);
				i++;
				cy = atof(argv[i]);
				i++;
				cz = atof(argv[i]);
				i++;
				FL.setParameters(Color(ax, ay, az), Color(cx, cy, cz));
				O = FL << O;
				O.save(name, "ppm");
				
				cout << "Linear Filter Applied!" << endl;
			}
			else if (strcmp(argv[i], "gamma") == 0) {

				i++;
				cout << "Applying Gamma Filter......." << endl;
				gp = atof(argv[i++]);
				//cout << gp << endl;
				FG.setParameters(gp);
				O = FG << O;
				O.save(name, "ppm");
				
				cout << "Gamma Filter Applied!" << endl;

			}
			else {
				
				i++;

			}

		}//end while


	}//end if/else





}//end main