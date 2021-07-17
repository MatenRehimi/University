#ifndef STACK_H
#define STACK_H

#include "vector.h"
using pep::vector;

#include<string>
using std::string;

#include <iostream>
using std::cout;
using std::endl;

#include <sstream>
using std::stringstream;
// TODO: Your code for question 3 goes here

class Stack {

	private:
		int top;
		vector<double> sVector;

	public:
		Stack() {
			top = -1;
		}

		bool empty() const {
			return (top == -1);
		}

		double pop() {
			if (!empty()) {

				double elem = sVector.back();
				sVector.pop_back();
				top--;
				return elem;
			}
		}

		void push(const double elem) {
			sVector.push_back(elem);
			top++;
		}



};

double evaluate(string line) {

		stringstream ss(line);
		vector<string> words;

		while (ss >> line) {
			words.push_back(line);
			cout << line << endl;
		}
		Stack stack;
		double tempDouble = 0;

		for (int i=0;i<words.size();i++) {
			if (words[i] != "+" && words[i] != "*" && words[i] != "/" && words[i] != "-") {
				stack.push(std::stod(words[i]));

			}
			if (words[i] == "+") {
				stack.push(stack.pop() + stack.pop());

			}
			if (words[i] == "-") {
				tempDouble = stack.pop();
				stack.push(stack.pop()-tempDouble);
			}
			if (words[i] == "*") {
				stack.push(stack.pop()*stack.pop());
			}
			if (words[i] == "/") {
				tempDouble = stack.pop();
				stack.push(stack.pop()/tempDouble);
			}

		}

		return stack.pop();

	}


// Do not write anything below this line

#endif
