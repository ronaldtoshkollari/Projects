#include <iostream>
#include "Image.h"
#include "Filter.h"
#include "FilterLinear.h"
#include "Vec3.h"

void FilterLinear::setParameters(const Color x, const Color y) {
	a.x = x.x;
	a.y = x.y;
	a.z = x.z;
	c.x = y.x;
	c.y = y.y;
	c.z = y.z;
}//end of setParameters

Image FilterLinear::operator<< (const Image & image) {
	Image I(image);
	unsigned int x, y;
	for (x = 0; x < image.getWidth(); x++) {
		for (y = 0; y < image.getHeight(); y++) {
			Color V = a * (image.getElement(x, y)) + c;
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
