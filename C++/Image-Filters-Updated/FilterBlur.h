#ifndef _FilterBlur
#define _FilterBlur


#include <iostream>
#include "Image.h"
#include "Filter.h"
#include "Vec3.h"
#include "Array.h"


class FilterBlur : public Filter , public Array<float>{

public:
	FilterBlur() {};
	~FilterBlur() {};
	void setParameters(int x);
	Image operator <<(const Image &image);

private:
	int N;

};


#endif // !_FilterBlur
