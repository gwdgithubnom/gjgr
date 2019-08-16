#pragma once
#include <iostream>
#include <iomanip>
#include <vector>
using namespace std;

class MinConflict
{
public:
	MinConflict(int numOfQueens);
	bool  CheckSatus(); // 检测是否达到最优解
	int   CalcuConflicts(size_t row, size_t column);
	void  MinConflictAlgorithm();
	void  PutQueen(size_t row, size_t column);
	void  RemoveQueen(size_t row, size_t column);
	void  Print();       // 打印最优解
	void  PrintConflict();

private:
	vector<int> m_chessBoard;
	size_t m_QueenNum;
	vector<int> m_columnConflict;
	vector<int> m_mainDiaConflict;       // 主对角线方向的映射规则 (i, j) --> m_QueenNum-1-i + j
	vector<int> m_counterDiaConflict;    // 副对角线方向的映射规则 (i, j) --> m_QueenNum-1-i + m_QueenNum-1-j
};