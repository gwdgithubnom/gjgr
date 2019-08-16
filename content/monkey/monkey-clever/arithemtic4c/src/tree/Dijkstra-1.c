#include <iostream>
#include <stdio.h>
#include <limits.h>
using namespace std;
const int V = 9; //���嶥�����

//��δ������SPT�ļ���T�У�ѡȡһ����S���ϵ���̾���Ķ��㡣
int getMinIndex(int dist[V], bool sptSet[V]) {
	   int min = INT_MAX, min_index;
	   for (int v = 0; v < V; v++)
	     if (sptSet[v] == false && dist[v] < min)
	         min = dist[v], min_index = v;
	   return min_index;
}

// ��ӡ���
void printSolution(int dist[], int n) {
	printf("Vertex   Distance from Source\n");
	for (int i = 0; i < V; i++)
		printf("%d \t\t %d\n", i, dist[i]);
}

//source ����Դ��
void dijkstra(int graph[V][V], int source) {
	int dist[V];     // �洢�������Դ�㵽 i�ľ���

	bool sptSet[V]; // sptSet[i]=true �������i������SPT��

	// ��ʼ��. 0�����ɴ�
	for (int i = 0; i < V; i++){
		dist[i] = (graph[source][i] == 0 ? INT_MAX:graph[source][i]);
		sptSet[i] = false;
	}

	// Դ�㣬��������Ϊ0. ������SPT
	dist[source] = 0;
	sptSet[source] = true;

	// ����V-1�Σ���˲��ü���Դ���ˣ���ʣ��V-1����Ҫ����Ķ��㡣
	for (int count = 0; count < V - 1; count++) {
		// u����T�����У���S���Ͼ�����С�ĵ�
		int u = getMinIndex(dist, sptSet);

		// ����SPT��
		sptSet[u] = true;

		//���µ�V�ľ��롣�������ΪBellman-Ford�е��ɳڲ���
		for (int v = 0; v < V; v++)
			if (!sptSet[v] && graph[u][v] && dist[u] != INT_MAX
					&& dist[u] + graph[u][v] < dist[v])
				dist[v] = dist[u] + graph[u][v];
	}

	printSolution(dist, V);
}

int main() {
	/* �������е�ͼΪ�� */
	int graph[V][V] =
			{ { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, {
					0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
					{ 0, 0, 0, 9, 0, 10, 0, 0, 0 },
					{ 0, 0, 4, 0, 10, 0, 2, 0, 0 },
					{ 0, 0, 0, 14, 0, 2, 0, 1, 6 },
					{ 8, 11, 0, 0, 0, 0, 1, 0, 7 },
					{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

	dijkstra(graph, 0);

	return 0;
}
