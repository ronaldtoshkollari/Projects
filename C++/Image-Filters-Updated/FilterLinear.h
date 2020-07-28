#ifndef _FilterLinear_
#define _FilterLinear

#include <iostream>
#include "Image.h"
#include "Filter.h"
#include "Vec3.h"


class FilterLinear : public Filter {
public:
	FilterLinear() {};
	~FilterLinear() {};
	void setParameters(const Color x, const Color y);
	Image operator<< (const Image & image);

private:
	Color a;
	Color c;
};

#endif // !_FilterLinear_

