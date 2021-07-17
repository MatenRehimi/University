#ifndef MAXIMUM_GATES_H
#define MAXIMUM_GATES_H

#include <vector>
using std::vector;

#include <iostream>
using std::endl;
using std::cout;

// TODO: Your answer for question 2 goes here

int maximumGates(const vector<int> & arrivalTimes,const vector<int> & departedTimes) {

	int numberOfGates = 0;
	int maxNumberOfGates = 0;

	for (int i=0; i< arrivalTimes.size(); i++ ) {
		numberOfGates++;
		if (numberOfGates > maxNumberOfGates) {
			maxNumberOfGates = numberOfGates;
		}
		for (int k=0; k <= i; k++) {
			if (k < arrivalTimes.size()-1) {
				if (departedTimes[k] > arrivalTimes[k] && departedTimes[k] < arrivalTimes[k+1] ) {
				numberOfGates--;
				}
			}
			if (k == arrivalTimes.size()-1 && departedTimes[k] > arrivalTimes[k]) {
				numberOfGates--;
			}
		}

	}

	return maxNumberOfGates;
}
// Do not write any code below this line


#endif
