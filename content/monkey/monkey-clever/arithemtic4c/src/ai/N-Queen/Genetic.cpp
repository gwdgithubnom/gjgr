#include "Genetic.h"

Genetic::Genetic(int numOfQueens, int initialGroupNum)
{
	m_adaptive.resize(initialGroupNum, 0);
	m_accumuAdaptive.resize(initialGroupNum, 0);
	m_QueenNum = numOfQueens;
	m_BestAdaptive = 0;
	m_NotSuccess = true;

	for (int i = numOfQueens - 1; i >= 1; --i)
	{
		m_BestAdaptive += i;  // 计算最佳适应度
	}

	SetPopulation();
}

void Genetic::SetPopulation()
{
	m_population.clear();
	vector<int> tmpState(m_QueenNum, 0);
	for (size_t i = 0; i < m_adaptive.size(); ++i)  // 初始种群
	{
		for (size_t j = 0; j < m_QueenNum; ++j)     // 随机生成状态
		{
			tmpState[j] = rand() % m_QueenNum;
		}
		m_population.push_back(tmpState);

		m_adaptive[i] = CalcuAdaptive(tmpState);    // 计算新生成种群的适应值
	}
}

// 计算适应值
double Genetic::CalcuAdaptive(vector<int> &state)
{
	size_t conflict = 0;
	for (size_t i = 0; i < m_QueenNum; ++i)
	{
		for (size_t j = i + 1; j < m_QueenNum; ++j)
		{
			if (state[i] == state[j] || abs(state[i] - state[j]) == j - i)
				conflict++;                 // 发现互相攻击的皇后对，conflict加一
		}
	}
	if (conflict == 0)  // 找到最优解
	{
		m_NotSuccess = false;
		m_BestOne = state;
	}
	return 1.0 / conflict;
}

// 物竞天择
void Genetic::Select()
{
	vector<vector<int>> NewPopulation;   // 新的种群
	m_accumuAdaptive[0] = m_adaptive[0];
	for (size_t i = 1; i < m_accumuAdaptive.size(); ++i)    // 累积适应值
	{
		m_accumuAdaptive[i] = m_accumuAdaptive[i - 1] + m_adaptive[i];
	}
	double totalAdaptive = m_accumuAdaptive[m_accumuAdaptive.size() - 1];
	for (size_t i = 0; i < m_population.size(); ++i)     // 进行选择
	{
		int    magnifyTotalAdaptive = totalAdaptive * 100000;  // 先乘以十万
		size_t random = rand()*rand() % magnifyTotalAdaptive;  // % 运算符要求整数
		double select = (double)random / 100000;               // 再除以十万

		vector<double>::iterator low;
		low = lower_bound(m_accumuAdaptive.begin(), m_accumuAdaptive.end(), select);  // 二分法查找
		size_t j = low - m_accumuAdaptive.begin();          // 被选中的种群的下标
		NewPopulation.push_back(m_population[j]);
	}
	// 更新种群
	m_population.clear();
	m_population = NewPopulation;
}

void Genetic::Crossover()
{
	for (size_t i = 0; i < m_population.size(); ++i, ++i)
	{
		//随机产生一个杂交单点，然后以单点为界，两个染色体呼唤单点两侧的
		//优质的基因片段无法长久遗传下去，随机性过强，很容易被切断
		/*size_t hybridSpot = rand() % m_QueenNum;
		for (size_t j = 0; j < hybridSpot; ++j)
		{
		swap(m_population[i][j], m_population[i + 1][j]);
		}
		for (size_t j = hybridSpot; j < m_QueenNum; ++j)
		{
		swap(m_population[i][j], m_population[i + 1][j]);
		}*/

		//以下采用中间段交换，经过实验统计。在8皇后（16个初始种群）时候，此方法比上面一种方法优化了38%
		size_t midA = m_QueenNum / 3;
		size_t midB = m_QueenNum * 2 / 3;
		for (size_t j = midA; j < midB; ++j)
		{
			swap(m_population[i][j], m_population[i + 1][j]);
		}

		//求解大于10的规模皇后的时候，把一个状态切割成一个小片段，交换
		/*bool change = true;
		size_t incre = 4;
		for (size_t i = 0; i < m_QueenNum; i += incre)
		{
		if (change)
		{
		for (size_t j = i; j < i + incre && j < m_QueenNum; ++j)
		{
		swap(m_population[i][j], m_population[i + 1][j]);
		}
		change ^= 1;
		}
		}*/
	}
}

// 发生杂交的两个state，不是紧挨着的。上面的算法，是紧挨着的
void Genetic::Crossover2()
{
	size_t first = 0;
	size_t row1;
	for (size_t row2 = 0; row2 < m_population.size(); ++row2)
	{
		if (rand() % 2)  // 50%概率
		{
			++first;
			if (first % 2 == 0)    // 一次row1“追赶上”row2，一次row1和row2交换
			{
				size_t crossPoint = rand() % (m_QueenNum - 1);
				for (size_t i = crossPoint; i < m_QueenNum; ++i)
				{
					swap(m_population[row1][i], m_population[row2][i]);
				}
			}
			else
			{
				row1 = row2;
			}
		}
	}
}

void Genetic::Mutate()
{
	//单点随机突变
	size_t mutateSpot = 0;
	for (size_t i = 0; i < m_population.size(); ++i)
	{
		if (rand() % 2 == 0)
		{
			mutateSpot = rand() % m_QueenNum;
			m_population[i][mutateSpot] = rand() % m_QueenNum;
		}
		m_adaptive[i] = CalcuAdaptive(m_population[i]);  // 更新适应值
	}
}

void Genetic::GeneticAlgorithm()
{
	//int counter = 0;
	while (m_NotSuccess)
	{
		Select();
		Crossover();
		Mutate();
		//++counter;
	}
	//cout << counter << endl;
	//cout << " Genetic成功找到最优解了! 遗传了" << counter << "次" << endl;
	//Print();
}

void Genetic::Print()
{
	for (int i = 0; i < m_BestOne.size(); ++i)
	{
		cout << setw(3) << i + 1 << "  ";
		for (int j = 0; j < m_BestOne.size(); ++j)
		{
			if (j == m_BestOne[i])
				cout << "Q ";
			else
				cout << ". ";
		}
		cout << endl;
	}
	cout << endl;
}