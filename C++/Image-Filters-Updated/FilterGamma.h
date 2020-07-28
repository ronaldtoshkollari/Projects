#ifndef _FilterGamma_
#define _FilterGamma_

#include <iostream>
#include "Image.h"
#include "Filter.h"

class FilterGamma : public Filter {

public:
	FilterGamma() {};
	~FilterGamma() {};
	void setParameters(const float x);
	Image operator<< (const Image & image);
private:
	float g;


};




#endif // !_FilterGamma_

