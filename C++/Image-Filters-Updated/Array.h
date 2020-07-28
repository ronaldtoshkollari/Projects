#ifndef  _Array
#define _Array

#include <string>
#include <vector>
#include "Vec3.h"
#include <iostream>


using namespace std;



namespace math {
	typedef Vec3<float> Color;

	template <typename T>
	class Array {


	//Variables
	protected:
		vector<T> buffer;
		unsigned int width, height;



	public:

		/*
		
		
			METHODS OF CLASS
		

		*/

		const unsigned int getWidth() const {
			return width;
		}//end of getWidth()

		const unsigned int getHeight() const {
			return height;
		}//end of getHeight()

		vector<T> * getRawDataPtr() {
			return *buffer;
		}//end of getRawDataPtr()

		void setData(const vector<T> data_ptr) {
			//null current object
			if (width == 0 || height == 0 || buffer.size == 0) {
				cerr << "Please check x or y or buffer size";
			}
			else {
				buffer = data_ptr;
			}
		}//end of setData

		T getElement(unsigned int x, unsigned int y) const {

			if (x >= width && y >= height) {
				return T();
			}
			else {
				return buffer[(x + y * getWidth())];
			}//end if


		}//end of getElement

		void setElement(unsigned int x, unsigned int y, T & value) {

			if (getWidth() == 0 || getHeight() == 0 || buffer.empty()) {
				cerr << "Please check x or y or buffer size";
			}
			else {
				buffer[x + y * getWidth()] = value;
			}

		}//end of setElement

		T & operator () (unsigned int x, unsigned int y) const {
			return getElement(x, y);
		}//end of ()

		/*
		Array<T> & operator = (const Array<T> & right) {
			Array<T> temp(right.getWidth(), right.getHeight()); //NOT WORKING 
			temp.setData(right.buffer);

		}
		
		*/

		/*
		
		
		
		
			Constructors and Destructor
		
		
		
		
		*/

		Array<T>() {
			width = height = 0;
		}//default constructor

		Array<T>(unsigned int x, unsigned int y) : width(x), height(y) {
			buffer.resize(x*y);
		}//constructor with width and height

		Array<T>(unsigned int x, unsigned int y, const vector<T> *data_ptr) : width(x), height(y) {
			buffer.resize(x*y);
			buffer = *data_ptr;
		}//constuctor with width,height and data_ptr

		Array<T>(const Array &src) {
			width = src.width;
			height = src.height;
			buffer.resize(width*height);
			for (unsigned int i = 0; i < buffer.size(); i++) {
				buffer[i] = src.buffer[i];
			}//end for
		}//constructor that does deep copy

		~Array() {
			buffer.clear();
		}





	};//end of Array class




}//end of math namespace





#endif // ! _Array

