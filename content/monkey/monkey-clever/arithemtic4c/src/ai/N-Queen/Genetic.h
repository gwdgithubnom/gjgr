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
	double CalcuAdaptive(vector<int> &state);  // ������Ӧֵ�������๥���Ļʺ������
	void SetPopulation();
	void Select();     // ѡ��
	void Crossover();  // �ӽ�
	void Crossover2(); // ����һ���ӽ�����,��crossover()��ȣ��Ż���20%
	void Mutate();     // ����
	void GeneticAlgorithm();  //�����в����ۺ���һ��
	void Print();      // ��ӡ���Ž�


private:
	size_t  m_QueenNum;     // �ʺ�����
	size_t  m_BestAdaptive; // ���Ž����Ӧֵ
	bool    m_NotSuccess;   // �Ƿ�ɹ��ҵ����Ž�
	vector<int> m_BestOne;  // ���Ž�
	vector<vector<int> > m_population;     // ��Ⱥ
	vector<double>      m_adaptive;       // ��Ⱥ����Ӧֵ --> ��ͻ�����ĵ�����
	                                      // �Ϳα�����Ĵ�ͳ��Ӧֵ������ȣ���8�ʺ����������Ż���80%��������ˣ����Խ����ʮ�ʺ������
	vector<double>      m_accumuAdaptive; // �ۻ�����Ӧֵ
	//��������vector���±�֮���Ƕ�Ӧ�ģ������Ϊ�����ģ�
};
