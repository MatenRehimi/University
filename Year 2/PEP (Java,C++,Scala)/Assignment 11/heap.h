#ifndef HEAP_H
#define HEAP_H

#include <cstddef>
#include <new>

#include <iostream>
using std::cout;
using std::endl;

class MemControlBlock {
  
public:
    
    /** @brief True if the MemControlBlock is before a block of available memory */
    bool available;
    
    /** @brief The size of the following block of memory, in bytes */
    size_t size;
    
    MemControlBlock * previous;
    MemControlBlock * next;
    
    MemControlBlock(const bool availableIn, const int sizeIn)
        : available(availableIn), size(sizeIn),
          previous(nullptr), next(nullptr)
    {
    }
    
};

class Heap {

private:
    char * const memory;
    
    /** @brief The first MemControlBlock for the heap -- the head of the list */
    MemControlBlock * startOfHeap;
    
public:
    
    ~Heap() {
        delete [] memory;
    }
    
    // no copy constructor, move constructor, assignment operator, ...
    Heap(const Heap &) = delete;
    Heap(Heap &&) = delete;    
    Heap & operator=(const Heap &) = delete;
    Heap & operator=(Heap &&) = delete;
    
    Heap(const size_t sizeIn)
        : memory(new char[sizeIn]) {
            
        // make a MemControlBlock at the start of the reserved memory
        startOfHeap = new(memory) MemControlBlock(// true = is available
                                                  true, 
                                                  // size = the size of the heap - the size of the MemControlBlock a the start of the heap
                                                  sizeIn - sizeof(MemControlBlock)
                                                 );
    }

    /** @brief Used for debugging - get the address of the start of the heap */
    char* getStartOfHeap() const {
        return memory;
    }
    
    /** @brief Used for debugging -- print out the details of the MemControlBlocks on the heap */
    void print() const {
        MemControlBlock * curr = startOfHeap;
        
        for (int i = 0; curr; ++i, curr = curr->next) {
            cout << "Block " << i << ": ";
            if (curr->available) {
                cout << " free, ";
            } else {
                cout << " in use, ";
            }
            cout << curr->size << " bytes\n";
        }
            
    }
    
    
    /** @brief Request a block of memory of the given size
     * 
     * Uses the 'Worst Fit' algorithm to choose a suitable block of available memory
     *
     * Returns the memory address of the start of the requested memory
     * If no block is big enough, it returns nullptr.
     */
    char * allocateMemoryWorstFit(size_t requested) {
        // TODO: your code for allocateMemory memory goes here

        if (requested%4 != 0) {
            requested = (4-requested%4)+requested;
        }


        MemControlBlock * curr = startOfHeap;


        MemControlBlock * largest = nullptr;

        while (curr) {

            if (!largest) {
                if (curr->available) {
                    largest = curr;
                }
            }else{
                if ((largest->size < curr->size) && curr->available) {
                    largest = curr;
                }
            }

            curr = curr->next;
        }

        largest->available = false; 
        int spare = largest->size-requested-sizeof(MemControlBlock);

        if (spare > 0) {

            char * start = reinterpret_cast<char*>(largest);
            MemControlBlock * newBlock = new(start+requested+sizeof(MemControlBlock)) 
                MemControlBlock(true,spare);
            newBlock->next = largest->next;
            newBlock->previous = largest;
            if (largest->next) {
                (largest->next)->previous = newBlock;
            }
            largest->next = newBlock;
            largest->size=requested;
            return start+sizeof(MemControlBlock);
        }
        if (spare == -16) {
            largest->size = requested;
            largest->available = false;
            return reinterpret_cast<char*>(largest)+sizeof(MemControlBlock);
        }

        return nullptr;
        
    }
    
    /** @brief Deallocate the memory used by the object at the given address */
    void deallocateMemory(char * toDeallocate) {
        // TODO: your code for deallocateMemory memory goes here
        toDeallocate = toDeallocate - sizeof(MemControlBlock);
        MemControlBlock * node = reinterpret_cast<MemControlBlock *>(toDeallocate);
        node->available = true;
        
        if (node->previous) {
            if (node->previous->available) {
                node->previous->size += sizeof(MemControlBlock)+node->size;
                (node->previous)->next = node->next;
                if(node->next) {
                    (node->next)->previous = node->previous;
                    node->next = nullptr;
                }
                node->previous = nullptr;
            }
        }
        
        if (node->next) {
            if (node->next->available) {
                node->size += sizeof(MemControlBlock)+node->next->size;
                if (node->next->next) {
                    node->next = (node->next)->next;
                    ((node->next)->next)->previous = node;
                }   
                (node->next)->next = nullptr;
                (node->next)->previous = nullptr;
            }
        }
        
        
    }
};

#endif
