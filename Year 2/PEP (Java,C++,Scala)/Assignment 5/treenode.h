#ifndef TREENODE_H
#define TREENODE_H

#include <iostream>
using std::cout;
using std::cerr;
using std::endl;
using std::ostream;

#include <memory>
using std::unique_ptr;

#include <utility>
using std::pair;

// TODO your code for the TreeNode class goes here:

template <class T>
class TreeNode {

	public:

		T data;
		unique_ptr<TreeNode<T>> leftChild;
		unique_ptr<TreeNode<T>> rightChild;
		TreeNode<T> * parent;

		TreeNode(const T & dataIn) : data(dataIn), parent(nullptr) {
		}


		void setLeftChild(TreeNode<T>* child) {

			leftChild.reset(child);
			child->parent = this;
		}

		void setRightChild(TreeNode<T>* child) {
			rightChild.reset(child);
			child->parent = this;
		}

		ostream &write(ostream &os) const {

			if (leftChild) {
				leftChild->write(os);
			}
			os << " " << data << " ";

			if (rightChild) {
				rightChild->write(os);
			}

			return os;
		}
		
		int maxDepth() const {
			if (leftChild && rightChild) {
				if (leftChild->maxDepth() > rightChild->maxDepth()) {
					return 1 + (leftChild->maxDepth());
				}else{
					return 1+ (rightChild->maxDepth());
				}
			}
			
			if (leftChild) {
				return 1+ (leftChild->maxDepth());
			}
			else if (rightChild) {
				return  1+rightChild->maxDepth();
			}
			else{
				return 1;
			}
		}

};

template <class T>
class TreeNodeIterator {
	private:

		TreeNode<T> * current;

	public: 

		TreeNodeIterator(TreeNode<T>* currentIn) : current(currentIn) {

		}

		T & operator*() const {
			return current->data;
		}

		bool operator!=(const TreeNodeIterator & other) const {
			return (current != other.current);
		}

		bool operator==(const TreeNodeIterator & other) const {
			return (current == other.current);
		}

		int count = 0;

		TreeNodeIterator & operator++() {

			if (current->rightChild) {
				current = current->rightChild.get();
				while (current->leftChild) {
					current = current->leftChild.get();
				}
				return *this;
			}

			while (current->parent) {

				if (current->data < ((current->parent)->data)) {
					current = current->parent;
					return *this;
				}

				current = current->parent;

			}
			current = current->parent;
			return *this;
		}

};

// do not edit below this line

#endif
