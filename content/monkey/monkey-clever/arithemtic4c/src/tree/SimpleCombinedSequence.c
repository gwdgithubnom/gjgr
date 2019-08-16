#include<stdio.h>
#include<string.h>
using namespace std;
void print(int arr[],int s,int e){
	for(int i=s; i<=e; i++){
		printf("%d ", arr[i]);
	}
	puts("");
}
/*
 arr[] �������飬 data[]���浱ǰ�����
  start, end ʣ���������ʼλ��
  index �Ѿ��ҵ���������ĸ���
  r �ܹ���Ҫ��������ĸ���
  */
void printAllCombination(int arr[],int data[],int start, int end,int index , int r){
	//��Ϲ�r���ʹ�ӡ��������
	if(index == r ){
		for(int i=0; i<r; i++)
			printf("%d ", data[i]);
		puts("");
		return;
	}
	//���ʣ�µ����鲻��(r-index)����ֱ�ӷ��ء�
	if(start + (r-index) > end ) return;
	data[index] = arr[start];//��¼��ǰ����
	printAllCombination(arr,data ,start+1, end, index+1, r);
	printAllCombination(arr, data,start+1, end, index, r);
}
int main() {
	int arr[] = {1,2,3,4,5};
	int r = 3;
	int * data = new int[r];//���������
	printAllCombination(arr,data, 0, sizeof(arr)/sizeof(arr[0]) ,0 ,r);
	return 0;
}
