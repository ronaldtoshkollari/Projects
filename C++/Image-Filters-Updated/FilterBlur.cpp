#include <iostream>
#include <math.h>
#include "Image.h"
#include "Filter.h"
#include "FilterBlur.h"
#include "Array.h"
#include "Vec3.h"

using namespace math;
void FilterBlur::setParameters(int x) {
	N = x;
	for (unsigned int i = 0; i < N*N; i++) {
		buffer.push_back(1 / pow(N, 2));
	}
}

Image FilterBlur::operator<<(const Image & image) {
	Image I(image);
	unsigned int x, y;
	int m, n;

	for (x = 0; x < image.getWidth(); x++) {
		for (y = 0; y < image.getHeight(); y++) {
			Color c;
			for (m = -N / 2; m < N / 2; m++) {

				for (n = -N / 2; N < N / 2; N++) {

					if ((x + m < image.getWidth() && x + m >= 0) && ( (y+n < image.getHeight() && y+n >= 0))) {
						c += image.getElement(x + m, y + n)*getElement(m + N / 2, n + N / 2);
					}//end if

					if ((y + n < image.getHeight() && y + n >= 0)) {
						c += image.getElement(x + m, y)*getElement(m + N / 2, n + N / 2);

					}//end if

					if ((x + m < image.getWidth() && x + m >= 0) && ((y + n < image.getHeight() && y + n >= 0))) {
						c += image.getElement(m, y + n)*getElement(m + N / 2, n + N / 2);

					}//end if

				}//end for n


			}//end for m
			I.setElement(x, y, c);

		}//end for y
	}//end for x
	return I;

}