#ifndef CIRCULAR_BUFFER_H
#define CIRCULAR_BUFFER_H


#include "vector.h"
using pep::vector;

#include "iostream"
using std::cout;

#include "string"
using std::string;

using std::endl;

/** TODO: complete the definition of the class CircularBuffer here
 *
 * Its constructor should take the size of the buffer as an argument
 *
 * It needs to have the functions:
 * - count() which returns how many things are in the buffer
 * - full() which returns true iff the buffer is full
 * - add() which takes an element and adds it to the buffer (you can assume the buffer is not full)
 * - remove() which removes the next element from the buffer (you can assume the buffer is not empty)
 */
class CircularBuffer {
	private:
		int fIndex;
        int rIndex;
		vector<char> cBuffer;

	public:
		CircularBuffer(int sizeIn)
		:cBuffer(sizeIn), fIndex(0),rIndex(0) {

		}
 
    	int count() const {
    		return (rIndex-fIndex+cBuffer.size())%cBuffer.size();
    	}

        bool full() const {
            return (fIndex == rIndex); 
        }

        void add(const char & element) {

            cBuffer[rIndex]=element;
            rIndex = (rIndex+1)%cBuffer.size();
        }

        char remove()  {

           char e = cBuffer[fIndex];
           fIndex = (fIndex+1)%cBuffer.size();      
           return e;
        }
};


// don't write any code below this line

#endif
