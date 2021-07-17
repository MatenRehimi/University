#ifndef KNIGHTS_H
#define KNIGHTS_H

#include <utility>
#include <vector>
#include <algorithm>
#include <numeric>
#include <iostream>
#include <cstdint>

// Do not add any extra #includes without asking on the KEATS discussion forum

using std::pair;
using std::make_pair;
using std::vector;
using std::find;
using std::copy_if;

typedef vector<pair<int,int> > Path;

/** Helper function: adds two pairs of ints */
pair<int,int> operator+(const pair<int,int> & a, const pair<int,int> & b) {
    return make_pair(a.first + b.first, a.second + b.second);
}

// TODO - your code goes here
vector<pair<int,int>> moves(pair<int,int> position) {
	int x = position.first;
	int y = position.second;
	return {{x+1,y-2}, {x+2,y-1}, {x+2,y+1}, {x+1,y+2}, 
		   {x-1,y+2}, {x-2,y+1}, {x-2,y-1}, {x-1,y-2}};
}

vector<pair<int,int>> legal_moves(int size, Path path, pair<int,int> position) {
	vector<pair<int,int>> possibleMoves = moves(position);
	vector<pair<int,int>> legalMoves;

	copy_if(possibleMoves.begin() ,possibleMoves.end(), back_inserter(legalMoves),
		[size,path](const pair<int,int> x) {
			return (x.first >= 0 && x.first < size && x.second >= 0 && x.second < size 
				&& (find(path.begin(),path.end(),x) == path.end()));
		});
	return legalMoves;
}

pair<Path,bool> first_tour(int size, Path path) {
	if (path.size() == size * size) {
		return {path,true};
	}else{
		vector<pair<int,int>> currentLegalMoves = legal_moves(size,path,path[path.size()-1]);

		for (int i = 0 ; i < currentLegalMoves.size(); i++) {

			Path copy = path;
			copy.push_back(currentLegalMoves[i]);
			pair<Path,bool> possibleAnswer = first_tour(size,copy);
			
			if(possibleAnswer.second) {
				return possibleAnswer;
			}
		}
		return {path,false};
	}	
}


// Do not edit below this line

#endif
