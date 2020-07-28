#ifndef _IMAGE
#define _IMAGE


#include "ppm/ppm.h"
#include "Array.h"
#include "Vec3.h"
#include <iostream>
#include <string>

using namespace std;
using namespace imaging;
using namespace math;

namespace math {

	class Image : public Array<Color> {

		/*
		
			CONSTRUCTORS AND DESTRUCTOR OF IMAGE CLASS

		*/
	public:

		Image() : math::Array<Color>() {};

		Image(unsigned int x, unsigned int y) : math::Array<Color>(x, y) {};

		~Image() {};

		/*
		
		
			METHODS OF CLASS
		
		
		*/

		bool load(const std::string filename, const std::string & format);
		bool save(const std::string & filename, const std::string & format);





	};//end of Image class
	







}//end of math namespace

#endif // !_IMAGE
