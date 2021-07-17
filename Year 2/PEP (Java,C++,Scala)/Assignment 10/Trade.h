#ifndef TRADE_H
#define TRADE_H

#include <iostream>
#include <algorithm>
#include <numeric>
#include <utility>
#include <cstdint>
#include <iterator>

using std::cout;
using std::endl;

using std::pair;
using std::min_element;
using std::accumulate;


// Do not add any extra #includes without asking on the KEATS discussion forum

// TODO your code goes here:

template <class iterator>

int bestProfit(iterator begin, iterator end) {

	pair<int,int> tempPair = {*begin,0};

	pair<int,int> res0 = accumulate(begin,end,tempPair, 
		[](const pair<int,int> f, const int s) {
			if (s < f.first) {
				return pair<int,int>(s,f.second);
			}
			else if (s > f.first) {
				if (s-f.first > f.second) return pair<int,int>(f.first,s-f.first);
				else return pair<int,int>(f.first,f.second);
			} 
			else {
				return pair<int,int>(f.first,f.second);
			} 
		});
	
	return res0.second;
}





// Don't write any code below this line

#endif
