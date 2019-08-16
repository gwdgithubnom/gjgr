#include "MinConflict.h"

MinConflict::MinConflict(int numOfQueens)
{
	m_QueenNum = numOfQueens;
	m_chessBoard.resize(m_QueenNum, 0);
	m_columnConflict.resize(m_QueenNum, 0);
	m_mainDiaConflict.resize(m_QueenNum * 2 - 1, 0);
	m_counterDiaConflict.resize(m_QueenNum * 2 - 1, 0);

	for (size_t row = 0; row < m_QueenNum; ++row)
	{
		int column = rand() % m_QueenNum; // 随机生成一个种群
		m_chessBoard[row] = column;
		PutQueen(row, column);
	}
}

// 更新这三个vector，添加一个皇后
void  MinConflict::PutQueen(size_t row, size_t column)
{
	//m_chessBoard[row] = column;  // 调试可视化
	m_columnConflict[column]++;
	m_mainDiaConflict[m_QueenNum - 1 - row + column]++;
	m_counterDiaConflict[2 * m_QueenNum - 2 - row - column]++;
}

// 更新这三个vector，移除某一个皇后
void  MinConflict::RemoveQueen(size_t row, size_t column)
{
	//m_chessBoard[row] = m_QueenNum;  // 调试可视化
	m_columnConflict[column]--;
	m_mainDiaConflict[m_QueenNum - 1 - row + column]--;
	m_counterDiaConflict[2 * m_QueenNum - 2 - row - column]--;
}

// 检测是否是出于最优解状态
bool MinConflict::CheckSatus()
{// 每一列只能有一个，每一个对角线方向不可能有两个。否则不可能是最优解
	for (size_t column = 0; column < m_columnConflict.size(); ++column)
	{
		if (m_columnConflict[column] != 1)
			return false;
	}
	for (size_t i = 0; i < m_mainDiaConflict.size(); ++i)
	{
		if (m_mainDiaConflict[i] >= 2 || m_counterDiaConflict[i] >= 2)
			return false;
	}
	return true;
}

int MinConflict::CalcuConflicts(size_t row, size_t column)
{
	return (m_columnConflict[column]
		+ m_mainDiaConflict[m_QueenNum - 1 - row + column]
		+ m_counterDiaConflict[2 * m_QueenNum - 2 - row - column]);
}

void MinConflict::MinConflictAlgorithm()
{
	bool m_isOptimal = CheckSatus();
	size_t counter = 0;
	while (!m_isOptimal)
	{
		//Print();
		//PrintConflict();
		//cout << endl;
		counter++;
		for (size_t row = 0; row < m_QueenNum; ++row)
		{
			size_t minConflict = 100000000;
			size_t tmpConflict = 100000000;
			size_t minColumn;

			size_t curColumn = m_chessBoard[row];
			RemoveQueen(row, curColumn);

			for (size_t column = 0; column < m_QueenNum; ++column)
			{
				tmpConflict = CalcuConflicts(row, column);
				if (tmpConflict < minConflict)
				{
					minConflict = tmpConflict;  // 找到最小冲突
					minColumn = column;         // 保存最小冲突时候的列数
				}
				else if (tmpConflict == minConflict && rand() % 2)
				{// 如果最小冲突值相等的话，有一半的可能移动，防止陷入局部最佳，而不是全局最佳
					minColumn = column;
				}
			}
			m_chessBoard[row] = minColumn;
			PutQueen(row, minColumn);

			m_isOptimal = CheckSatus();
			if (m_isOptimal)
				break;

			if (counter > 200)
			{
				m_isOptimal = true;
				break;
			}
		}
	}
	//cout << " CSP（最小冲突）成功找到" << m_QueenNum << "皇后的解! 迭代了" << counter << "次" << endl;
	//Print();
}

void MinConflict::Print()
{
	for (int i = 0; i < m_QueenNum; ++i)
	{
		cout << setw(3) << i + 1 << "  ";
		for (int j = 0; j < m_QueenNum; ++j)
		{
			if (j == m_chessBoard[i])
				cout << "Q ";
			else
				cout << ". ";
		}
		cout << endl;
	}
	cout << endl;
}

void MinConflict::PrintConflict()
{
	cout << "    ";
	for (size_t i = 0; i < m_columnConflict.size(); ++i)
		cout << m_columnConflict[i] << " ";
	cout << endl;

	cout << "    ";
	for (size_t i = 0; i < m_mainDiaConflict.size(); ++i)
		cout << m_mainDiaConflict[i] << " ";
	cout << endl;

	cout << "    ";
	for (size_t i = 0; i < m_counterDiaConflict.size(); ++i)
		cout << m_counterDiaConflict[i] << " ";
	cout << endl;
}