#pragma once
#include <iostream>
#include <iomanip>
#include <vector>
using namespace std;

class MinConflict
{
public:
	MinConflict(int numOfQueens);
	bool  CheckSatus(); // ����Ƿ�ﵽ���Ž�
	int   CalcuConflicts(size_t row, size_t column);
	void  MinConflictAlgorithm();
	void  PutQueen(size_t row, size_t column);
	void  RemoveQueen(size_t row, size_t column);
	void  Print();       // ��ӡ���Ž�
	void  PrintConflict();

private:
	vector<int> m_chessBoard;
	size_t m_QueenNum;
	vector<int> m_columnConflict;
	vector<int> m_mainDiaConflict;       // ���Խ��߷����ӳ����� (i, j) --> m_QueenNum-1-i + j
	vector<int> m_counterDiaConflict;    // ���Խ��߷����ӳ����� (i, j) --> m_QueenNum-1-i + m_QueenNum-1-j
};