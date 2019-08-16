#pragma once
#include <vector>
#include <cstdlib>
#include <iomanip>
#include <iostream>
using namespace std;

class NQueensBacktrack
{
public:
	NQueensBacktrack() :m_queens(0), m_counter(0), m_succussFindOne(false){};
	NQueensBacktrack(int n) :m_queens(n, 0), m_counter(0){};
	bool Check(int row, int column);
	void Backtrack(int row);
	void Print();

private:
	vector<int> m_queens;
	int  m_counter;
	bool m_succussFindOne;
};