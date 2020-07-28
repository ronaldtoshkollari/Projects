#include <iostream>
#include <math.h>
#include "Image.h"
#include "Filter.h"
#include "FilterGamma.h"
#include "Vec3.h"


void FilterGamma::setParameters(const float x) {
	g = x;
}

Image FilterGamma::operator<<(const Image & image) {
	Image I(image);
	unsigned int x, y;

	for (x = 0; x < image.getWidth(); x++) {
		for (y = 0; y < image.getHeight(); y++) {
			Color S = image.getElement(x, y);
			Color V(pow(S.x, g), pow(S.y, g), pow(S.z, g));

			//bound checking
			if (V.x > 1) V.x = 1;
			if (V.y > 1) V.y = 1;
			if (V.z > 1) V.z = 1;
			if (V.x < 0) V.x = 0;
			if (V.y < 0) V.y = 0;
			if (V.z < 0) V.z = 0;
			I.setElement(x, y, V);
		}//end for y
	}//end for x
	return I;
}