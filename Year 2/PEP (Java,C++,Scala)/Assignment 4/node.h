#ifndef NODE_H
#define NODE_H

#include <iostream>
using std::cout;
using std::cerr;
using std::endl;

// TODO your code for the Node class goes here:
// (Note the 'NodeIterator' class will report lots of errors until Node exists

template <class T>
class Node {
public:
	T data;
	Node* next;
	Node* previous;


	Node(T dataIn) :data(dataIn) {
		next = nullptr;
		previous = nullptr;
	}

};



template<typename T>
class NodeIterator {
  
private:
    
    Node<T>* current;
    
public:
    

    NodeIterator(Node<T>* currentIn)
        : current(currentIn) {        
    }

    T & operator*() {
        return current->data;
    }

    // TODO: complete the code for NodeIterator here
        
    NodeIterator & operator++() {
            current = current->next;
        	return *this;
        }


        bool operator!=(const NodeIterator & other ) const {
        	return (current != other.current);
        }

        bool operator==(const NodeIterator & other) const {
        	return (current == other.current);
        }

    
    
};

// do not edit below this line

#endif
