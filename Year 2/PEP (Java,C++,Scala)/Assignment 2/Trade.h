#ifndef TRADE_H
#define TRADE_H


#include "vector.h"
using pep::vector;


class Trade {
protected:
    int buyTime;
    int sellTime;

public:
    Trade(const int buyTimeIn, const int sellTimeIn)
        : buyTime(buyTimeIn), sellTime(sellTimeIn) {
    }

    int getBuyTime() const {
        return buyTime;
    }

    int getSellTime() const {
        return sellTime;
    }

};

// TODO your code goes here:

Trade bestBuySellTime(vector<int> prices) {
	int buyTime = 0;
	int sellTime = prices.size()-1;
	int buyPointer = 0;
	int sellPointer = prices.size()-1;

	bool tempBool = true;

	while (tempBool) {

		if(buyPointer < sellTime) {
			if (prices[buyPointer] > prices[buyPointer+1] && prices[buyTime] > prices[buyPointer+1]) {
					buyTime = buyPointer+1;;
			}
			buyPointer++;

		}else{
			tempBool = false;
		}

		if (sellPointer > buyTime) {
			if (prices[sellPointer-1] > prices[sellPointer] && prices[sellTime] < prices[sellPointer-1] ) {
				sellTime = sellPointer-1;
			}
				sellPointer--;

		}else{
			tempBool = false;
		}
	}

	return Trade(buyTime,sellTime);
}


// Don't write any code below this line

#endif
