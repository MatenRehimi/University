#ifndef SUDOKU_H
#define SUDOKU_H

#include "Searchable.h"

#include <vector>
using std::vector;

#include <set>
using std::set;

#include <iostream>
using std::endl;
using std::cout;

#include<cmath>
using std::sqrt;

#include<memory>
using std::unique_ptr;
using std::move;

// TODO: Your Sudoku class goes here:

class Sudoku : public Searchable {
	private:
		vector<vector<set<int>>> board;

	public:
		Sudoku(int size)  {
			set<int> numbers;
			for (int i=1; i <= size; i++) {
				numbers.insert(i);
			}
			vector<vector<set<int>>> boardIn(size,vector<set<int>>(size,numbers));
			board = boardIn;

		}

		Sudoku(const Sudoku & other) :board(other.board) {

		}

		int getSquare(int row, int col) {
			if (board[row][col].size() == 1) {
				return *(board[row][col].begin());
			}else{
				return -1;
			}
		}

		bool setSquare(int row, int col, int value) {
			if (row < 0 || row >= board.size() || col < 0 || col >= board.size()) {
				return false;
			}

			int root = sqrt(board.size());
			int topLeftRow;
			int topLeftCol;

			board[row][col] = {value};

			bool repeat = true;
			int size = 0;
			set<int> repeats = {};
			int num1;
			int num2;
			int num3;

			while (repeat) {
				repeat = false;
				for (int i=0;i<board.size();i++) {
					for (int j=0;j<board.size();j++) {

						//Double sets
						if (board[i][j].size() == 2) {
							repeats = board[i][j];
							for (int t = 0; t < board.size();t++) {
								if (t != j) {
									if (repeats == board[i][t]) {
										size = board[i][t].size();
										auto itr = board[i][t].begin();
										num1 = *itr;
										itr++;
										num2 = *itr;
									for (int y = 0; y < board.size(); y++) {
										if (y != t && y != j) {
											board[i][y].erase(num1);
											board[i][y].erase(num2);
										}
									}
								
									if (board[i][t].size() != size) {
										repeat = true;
									}
								}
							}

								if (t != i) {
									if (repeats == board[t][j]) {
										size = board[t][j].size();
										auto itr = board[t][j].begin();
										num1 = *itr;
										itr++;
										num2 = *itr;

										for (int p = 0; p < board.size(); p++) {
											if (p != t && p != i) {
												board[p][j].erase(num1);
												board[p][j].erase(num2);
											}
										}
					
										if (board[t][j].size() != size) {
											repeat = true;
										}
									}
								}
								
							}
						}

						//triple sets
						if (board[i][j].size() == 3) {

							repeats = board[i][j];
							for (int t = 0; t < board.size();t++) {
								if (t != j) {

									if (repeats == board[i][t]) {
										for (int z = 0; z <board.size(); z++) {
											if (z!=j && z!=t) {
												if (repeats == board[i][z]){
													size = board[i][t].size();
													auto itr = board[i][t].begin();
													num1 = *itr;
													itr++;
													num2 = *itr;
													itr++;
													num3 = *itr;
													for (int y = 0; y < board.size(); y++) {
														if (y != t && y != j && y != z) {
															board[i][y].erase(num1);
															board[i][y].erase(num2);
															board[i][y].erase(num3);
														}
													}
													if (board[i][t].size() != size) {
														repeat = true;
													}
												}
											}
										}
									}
							}

								if (t != i) {
									if (repeats == board[t][j]) {
										for (int b = 0; b < board.size(); b++) {
											if (b != t && b != i) {
												if (repeats == board[b][j]) {
													size = board[t][j].size();
													auto itr = board[t][j].begin();
													num1 = *itr;
													itr++;
													num2 = *itr;
													itr++;
													num3 = *itr;
													for (int p = 0; p < board.size(); p++) {
														if (p != t && p!=i && p!=b) {
															board[p][j].erase(num1);
															board[p][j].erase(num2);
															board[p][j].erase(num3);
														}
													}
													if (board[t][j].size() != size) {
														repeat = true;
													}

												}
											}
										}
									}
								} 
							}
						}

						if (board[i][j].size() == 1) { 

							for (int k = 0; k< board.size(); k++) {

								if (k != j) {
									size = board[i][k].size();
									board[i][k].erase(*(board[i][j].begin()));
									if (board[i][k].size() != size) {
										repeat = true;
									}
								}
								if (k!=i) {
									size = board[k][j].size();
									board[k][j].erase(*(board[i][j].begin()));
									if (board[k][j].size () != size) {
										repeat = true;
									}
								}
							}

							topLeftRow = i - (i%root);
							topLeftCol = j - (j%root);

							for (int q=topLeftRow; q < topLeftRow+3; q++) {
								for (int w=topLeftCol; w < topLeftCol+3; w++) {
									if (q != i || w != j) {
										size = board[q][w].size();
										board[q][w].erase(*(board[i][j].begin()));
										if (board[q][w].size() != size) {
											repeat = true;
										}
									}
								}
							}

						}

						if (board[i][j].empty()) {
							return false;
						}
					}
				}	
			}

			return true;
		}

		virtual bool isSolution() const {
			for (int i=0; i < board.size(); i++) {
				for (int j=0; j < board.size(); j++) {
					if (board[i][j].size() != 1) {
						return false;
					}
				}
			}
			return true;
		}

		virtual void write(ostream & o) const {
			for (int i=0; i < board.size(); i++) {
				for (int j=0; j < board.size(); j++) {
					if (board[i][j].size() == 1) {
						if ((*board[i][j].begin()) < 10) {
							o << " " << *(board[i][j].begin());
						}else{
							o << *(board[i][j].begin());
						}

					}else{
						o << "  ";
					}
				}
				o << endl;
			}
		}

		virtual int heuristicValue() const {
			int count = 0;
			for (int i = 0; i < board.size(); i++) {
				for (int j = 0; j < board.size();j++) {
					if (board[i][j].size() > 1) {
						count++;
					}
				}
			}
			return count;
		}

		virtual vector<unique_ptr<Searchable> > successors() const {

			vector<unique_ptr<Searchable> > possibleSolutions;

			for (int i=0; i <board.size(); i++) {
				for (int j=0; j<board.size(); j++) {
					if (board[i][j].size() > 1) {
						auto itr = board[i][j].begin();
						while (itr != board[i][j].end()) {

							unique_ptr<Sudoku> pointer(new Sudoku(*this));

							if (pointer->setSquare(i,j,*itr)) {
								possibleSolutions.push_back(move(pointer));
							}

							itr++;

						}
						
						if (possibleSolutions.size() == 1 ) {
							if (!((*possibleSolutions[0]).isSolution())) {
								return (*possibleSolutions[0]).successors();
							}
						}

						return possibleSolutions;
					}
				}
			}
			return possibleSolutions;
		} 

};
#endif
