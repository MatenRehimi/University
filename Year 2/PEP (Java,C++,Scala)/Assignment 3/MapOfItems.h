#ifndef MAPOFITEMS_H
#define MAPOFITEMS_H

#include "Item.h"

#include "vector"
using std::vector;

#include <climits>

// TODO: your code goes here

class MapOfItems {
private:
	vector<Item> items;
	int currentItem;
	double currentDuration;
	Item previousItem;
	double walkingSpeeds;

public:
	MapOfItems()  {

		currentItem=0;
		currentDuration=0;
	}
	void addItem(Item itemIn) {
		items.push_back(itemIn);
	}
	int size() const {
		return items.size();
	}
	double visit (Item item) {
		double walkingTime =0;
		walkingTime = ((double)previousItem.distanceTo(item))/walkingSpeeds;

		if (currentDuration+walkingTime-item.getSeconds() > 900) {
			return INT_MAX;
		}

		if (currentDuration+walkingTime < item.getSeconds() ) {
			return item.getSeconds();
		}

		return currentDuration + walkingTime;
	}

	vector<Item> getTour(double walkingSpeed) {

		walkingSpeeds = walkingSpeed;
		int minTimeAvailable = INT_MAX;
		for (int j = 0;j < items.size(); j++) {
			if (items[j].getSeconds() < minTimeAvailable ) {
				previousItem = items[j];
			}
		}

		currentDuration = previousItem.getSeconds();

		vector<Item> tour;
		vector<Item> tempItems = items;
		previousItem= tempItems[0];

		double minTime=INT_MAX;
		currentDuration = previousItem.getSeconds();

		while (currentDuration <= 3600 && tempItems.size() > 0) {

			for (int i = 0; i < tempItems.size();i++) {

				if ((visit(tempItems[i]) < minTime)) {
					minTime = visit(tempItems[i]);
					currentItem = i;

				}

			}

			currentDuration = minTime;

			if ((currentDuration ) > 3600) {
				break;
			}


			previousItem = tempItems[currentItem];
			tempItems.erase(tempItems.begin()+currentItem);
			tour.push_back(previousItem);

			minTime = INT_MAX;
		}

		return tour;
	}


};

// don't write any code below this line

#endif

