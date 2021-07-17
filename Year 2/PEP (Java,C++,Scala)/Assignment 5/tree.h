#ifndef TREE_H
#define TREE_H

#include "treenode.h"

// TODO your code goes here:

template <class T>
class BinarySearchTree {
	private:
		unique_ptr<TreeNode<T>> root;

	public:
		
		int calculateBalanceFactor(TreeNode<T> * node) {
			int leftNode = 0;
			int rightNode = 0;
			if (node->leftChild) {
					leftNode = node->leftChild->maxDepth();
				}
				if (node->rightChild) {
					rightNode = node->rightChild->maxDepth();
				}			
				
			return leftNode - rightNode;
		}

		void write(ostream & os) const {
			root->write(os);
		}

		TreeNode<T> * insert(const T & elem) {

			if (root == nullptr) {
				TreeNode<T> * currentNode = new TreeNode<T>(elem);
				root.reset(currentNode);
				return currentNode;
			}

			TreeNode<T> * currentNode = root.get();
			TreeNode<T> * previousNode = root.get();

			while (currentNode) {

				if (elem < currentNode->data) {
					previousNode = currentNode;
					currentNode = currentNode->leftChild.get();
				}
				else if (currentNode->data < elem) {
					previousNode = currentNode;
					currentNode = currentNode->rightChild.get();
				}
				else {
					return currentNode;
				}
			}

			currentNode = new TreeNode<T>(elem);

			if (elem < previousNode->data) {
				previousNode->setLeftChild(currentNode);
			}else{
				previousNode->setRightChild(currentNode);
			}
			
			//advanced code

			if (currentNode->parent->parent) {

				TreeNode<T> * topNode = currentNode->parent->parent;
				TreeNode<T> * middleNode = currentNode->parent;
	
				int bf1 = calculateBalanceFactor(topNode);
				int bf2 = calculateBalanceFactor(middleNode);

				//LEFT ROTATION
				if (bf1 == -2 && bf2 == -1) {
					if (topNode->parent) {
					
						if (middleNode->data < topNode->parent->data) {
							topNode->parent->leftChild.release();
							topNode->parent->setLeftChild(middleNode);
						}else{
							topNode->parent->rightChild.release();
							topNode->parent->setRightChild(middleNode);
						}
						middleNode->parent = topNode->parent;
					}else{
						root.release();
						root.reset(middleNode);
						middleNode->parent = nullptr;
						}

					middleNode->setLeftChild(topNode);
					topNode->rightChild.release();

					}
				
				//RIGHT ROTATION
				if (bf1 == 2 && bf2 == 1) {

					if (topNode->parent) {
					
						if (middleNode->data < topNode->parent->data) {
							topNode->parent->leftChild.release();
							topNode->parent->setLeftChild(middleNode);
						}else{
							topNode->parent->rightChild.release();
							topNode->parent->setRightChild(middleNode);
						}
						middleNode->parent = topNode->parent;
					}else{
						root.release();
						root.reset(middleNode);
						middleNode->parent = nullptr;
						}

					middleNode->setRightChild(topNode);
					topNode->parent = middleNode;
					topNode->leftChild.release();

				}

				//LEFT RIGHT ROTATION
				if (bf1 == 2 && bf2 == -1) {

					topNode->leftChild.release();
					topNode->setLeftChild(currentNode);

					currentNode->setLeftChild(middleNode);
					currentNode->parent = topNode;
					middleNode->rightChild.release();
					middleNode->parent = currentNode;

					middleNode= currentNode;

					if (topNode->parent) {
					
						if (middleNode->data < topNode->parent->data) {
							topNode->parent->leftChild.release();
							topNode->parent->setLeftChild(middleNode);
						}else{
							topNode->parent->rightChild.release();
							topNode->parent->setRightChild(middleNode);
						}
						middleNode->parent = topNode->parent;
					}else{
						root.release();
						root.reset(middleNode);
						middleNode->parent = nullptr;
						}

					middleNode->setRightChild(topNode);
					topNode->parent = middleNode;
					topNode->leftChild.release();

				}
				

				//RIGHT LEFT ROTATION
				if (bf1 == -2 && bf2 == 1) {
				
					topNode->rightChild.release();
					topNode->setRightChild(currentNode);

					currentNode->setRightChild(middleNode);
					currentNode->parent = topNode;
					middleNode->leftChild.release();
					middleNode->parent = currentNode;

					//left

					middleNode= currentNode;

					if (topNode->parent) {
					
						if (middleNode->data < topNode->parent->data) {
							topNode->parent->leftChild.release();
							topNode->parent->setLeftChild(middleNode);
						}else{
							topNode->parent->rightChild.release();
							topNode->parent->setRightChild(middleNode);
						}
						middleNode->parent = topNode->parent;
					}else{
						root.release();
						root.reset(middleNode);
						middleNode->parent = nullptr;
						}

					middleNode->setLeftChild(topNode);
					topNode->rightChild.release();

				}

			}

			return currentNode;


		}

		TreeNode<T> * find(const T & elem) {

			TreeNode<T> * currentNode = root.get();
			while (currentNode) {
				if (elem < currentNode->data) {
					currentNode = currentNode->leftChild.get();
				}
				else if (currentNode->data < elem) {
					currentNode = currentNode->rightChild.get();
				}
				else {
					return currentNode;
				}
			}
			return nullptr;
		}
		
		TreeNodeIterator<T> begin() const {
			TreeNode<T> * currentNode = root.get();
			if (currentNode) {
				while (currentNode->leftChild) {
				currentNode = currentNode->leftChild.get();
				}
			}
				return TreeNodeIterator<T>(currentNode);
			
		}

		TreeNodeIterator<T> end() const {
			return TreeNodeIterator<T>(nullptr);
		}

		int maxDepth() const {

			if (root) {
				return root->maxDepth();
			}
			return 0;
		}

};


// do not edit below this line

#endif
