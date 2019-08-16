#pragma once
#pragma warning(disable:4996)
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <Windows.h>
#include "NQueensBacktrack.h"
#include "Genetic.h"
#include "MinConflict.h"
using namespace std;

int main()
{
	srand((unsigned)time(NULL));  // 随机种子
	
	/*cout << " 请输入皇后的数量（1或者大于等于4）: ";
	int QNumber;
	cin >> QNumber;
	
	NQueensBacktrack trackback(QNumber);
	trackback.Backtrack(0);

	Genetic queens(QNumber, QNumber * 4);
	queens.GeneticAlgorithm();

	MinConflict queenConflict(QNumber);
	queenConflict.MinConflictAlgorithm();*/


	///////////////////////////////////////////////////////
	// 性能测试

	//freopen("Queen8_Gentic_Adaptive2.txt", "w", stdout);
	clock_t time2 = clock();
	
	//for (int i = 0; i < 1000; ++i)
	//{
	//	NQueensBacktrack myQueen(20);
	//	myQueen.Backtrack(0);
	//	//cout << i << endl;
	//}

	cout << "do"<<endl;
	for (int i = 0; i < 5; ++i)
	{
		Genetic Queens(30, 120);
		cout << "genetic"<<i << endl;
		Queens.GeneticAlgorithm();
	}


	for (int i = 0; i < 10; ++i)
	{
		MinConflict QueenConflict(1000);
		cout <<"minconfilict"<<i << endl;
		QueenConflict.MinConflictAlgorithm();
	}


	cout << "end"<<endl;
	///////////////////////////////////////////////////////

	//freopen("CON", "w", stdout);

	cout << "\n 花费了" << (double)(clock() - time2) / CLOCKS_PER_SEC << "秒" << endl;
	system("pause");
	return 0;
}