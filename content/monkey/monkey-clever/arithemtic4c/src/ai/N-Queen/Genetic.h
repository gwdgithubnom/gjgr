#pragma once
#include <iostream>
#include <cstdlib>
#include <iomanip>
#include <algorithm>
#include <vector>
#include <ctime>
#include <cmath>
#include <fstream>
using namespace std;

class Genetic
{
public:
	Genetic(int numOfQueens, int initialGroupNum);
	double CalcuAdaptive(vector<int> &state);  // 计算适应值（互不相攻击的皇后对数）
	void SetPopulation();
	void Select();     // 选择
	void Crossover();  // 杂交
	void Crossover2(); // 另外一种杂交策略,和crossover()相比，优化了20%
	void Mutate();     // 变异
	void GeneticAlgorithm();  //把所有步骤综合在一起
	void Print();      // 打印最优解


private:
	size_t  m_QueenNum;     // 皇后数量
	size_t  m_BestAdaptive; // 最优解的适应值
	bool    m_NotSuccess;   // 是否成功找到最优解
	vector<int> m_BestOne;  // 最优解
	vector<vector<int> > m_population;     // 种群
	vector<double>      m_adaptive;       // 种群的适应值 --> 冲突对数的倒数。
	                                      // 和课本上面的传统适应值方法相比，在8皇后问题上面优化了80%。不仅如此，可以解决几十皇后的问题
	vector<double>      m_accumuAdaptive; // 累积的适应值
	//上述三个vector的下标之间是对应的（可理解为关联的）
};
