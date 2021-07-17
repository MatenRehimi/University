#ifndef BEST_FIRST_SEARCH_H
#define BEST_FIRST_SEARCH_H

#include "Searchable.h"

#include <memory>
using std::unique_ptr;
using std::move;

#include <queue>
using std::priority_queue;

class BestFirstSearch {

protected:
	
	priority_queue<unique_ptr<Searchable>> PQ;

    /// Make sure you increment this every time you 'expand' a node, by getting it successors and putting them on the queue        
    int nodes = 0;
    
    
public:

    BestFirstSearch(std::unique_ptr<Searchable> && startFrom) {
        // TODO Put startFrom onto the queue:
    		PQ.push(move(startFrom));
    }
    
    int getNodesExpanded() const {
        return nodes;
    }
    
    Searchable * solve() {
            
        // TODO Implement search, returning a pointer to the solution (if one is found)
    
        while (!PQ.empty()) {
            if (PQ.top()->isSolution()) {
                return PQ.top().get(); // return the pointer 
            }

            ++nodes;
            
            unique_ptr<Searchable> current(move(const_cast<unique_ptr<Searchable> & >(PQ.top())));
            PQ.pop();
            vector<unique_ptr<Searchable>> successors = current->successors();
            
            for (auto & successor : successors) {
                         
                  PQ.push(move(successor));
             }

        }
        

        return nullptr;
    }
};


// Do not edit below this line

#endif
