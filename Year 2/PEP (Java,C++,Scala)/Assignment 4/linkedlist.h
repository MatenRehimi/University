#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include "node.h"

// TODO your code goes here:

template <class T> 
class LinkedList {
	private:
		Node<T>* head;
		Node<T>* tail;
		int listSize;

	public:
		LinkedList() : head(nullptr), tail(nullptr), listSize(0) { 
		}

		~LinkedList() {
			Node<T> * current;
			for (int i =0 ; i < listSize;i++) { 
			current = head;
			head = head->next;
            delete current; 
            
            }
		}

		void push_front(const T elem) {
			Node<T> *newNode = new Node<T>(elem);

			if (listSize == 0) {
				tail = newNode;
			}else{
				newNode->next =head;
				head->previous = newNode;

			}
			head=newNode;
			listSize++;

		}

		T front() const {
			return head->data;
		}

		void push_back(const T elem) {
			Node<T> *newNode = new Node<T>(elem);

			if (listSize == 0) {
				head = newNode;
				tail = newNode;
			}else{
				tail->next = newNode;
				newNode->previous = tail;
				tail = newNode;
			}
			listSize++;
		}

		T back() const {
			return tail->data;
		}

		int size() const {
			return listSize;
		}

		NodeIterator<T> begin() const{
			return NodeIterator<T>(head);
		}

		NodeIterator<T> end() const{
			return NodeIterator<T>(nullptr);
		}

};



// do not edit below this line

#endif
