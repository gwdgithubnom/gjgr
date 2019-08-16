#include "NQueensBacktrack.h"
using namespace std;

void NQueensBacktrack::Backtrack(int row)
{
	if (m_succussFindOne)
		return;

	for (int column = 0; column < m_queens.size(); ++column)
	{
		if (Check(row, column))
		{
			m_queens[row] = column;
			if (row == m_queens.size() - 1)  //�����һ�гɹ�����һ��Queen
			{
				m_succussFindOne = true;
				Print();
			}
			Backtrack(row + 1);
		}
	}
}

bool NQueensBacktrack::Check(int row, int column)
{
	for (int i = 0; i < row; ++i)
	{		       // �Խ��߻��๥��                            // ��ֱ���๥��
		if ((row - i) == abs(column - m_queens[i]) || m_queens[i] == column)
			return 0;
	}
	return 1;
}

void NQueensBacktrack::Print()
{
	cout << "\n\n ���ݷ���" << ++m_counter << "��������" << endl;
	for (int i = 0; i < m_queens.size(); ++i)
	{
		cout << setw(3) << i + 1 << "  ";
		for (int j = 0; j < m_queens.size(); ++j)
		{
			if (j == m_queens[i])
				cout << "Q ";
			else
				cout << ". ";
		}
		cout << endl;
	}
	cout << endl;
}