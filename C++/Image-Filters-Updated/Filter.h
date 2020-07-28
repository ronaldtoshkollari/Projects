#ifndef _Filter_
#define _Filter_

#include <iostream>
#include "Image.h"

class Filter {
public:

	virtual Image operator<< (const Image & image) = 0;
	Filter() {};
	~Filter() {};
};


#endif // !_Filter_
