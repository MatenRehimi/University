#ifndef ITEM_H
#define ITEM_H

// TODO: copy your Item.h file from assignment 1 over this file

#include<string>
using std::string;

#include <ostream>
using std::ostream;

#include <cmath>

class Item {
	private:
		float latitude;
		float longitude;
		string ID;
		int seconds;

	public:
		Item(float latitudeIn, float longitudeIn, string IDIn, int secondsIn)
			: latitude(latitudeIn), longitude(longitudeIn), ID(IDIn), seconds(secondsIn) {
			}

		Item() {}

		float getLatitude() const {
			return latitude;
		}
		float getLongitude() const {
			return longitude;
		}
		string getID() const {
			return ID;
		}
		int getSeconds() const {
			return seconds;
		}

		int distanceTo(Item & item) {

			float lat1 = latitude * 3.141592/180;
			float lon1 = longitude * 3.141592/180;

			float lat2 = item.getLatitude() * 3.141592/180;
			float lon2 = item.getLongitude() * 3.141592/180;


			float dlon = lon2- lon1;
			float dlat = lat2 - lat1;
			float a = pow((sin(dlat/2)),2) + cos(lat1)*cos(lat2)*pow((sin(dlon/2)),2);
			float c = 2 * atan2(sqrt(a), sqrt(1-a));
			float distance = 6373000*c;
			return distance;

		}

};

		ostream &operator<<(ostream &os,Item &item) {

			os << "{" << item.getLatitude() << ", " << item.getLongitude() << ", \"" << item.getID() << "\", " << item.getSeconds() << "}";

			return os;
		}

#endif

