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
 arr[] 输入数组， data[]保存当前的组合
  start, end 剩余数组的起始位置
  index 已经找到的组合数的个数
  r 总共需要的组合数的个数
  */
void printAllCombination(int arr[],int data[],int start, int end,int index , int r){
	//组合够r个就打印，并返回
	if(index == r ){
		for(int i=0; i<r; i++)
			printf("%d ", data[i]);
		puts("");
		return;
	}
	//如果剩下的数组不够(r-index)个就直接返回。
	if(start + (r-index) > end ) return;
	data[index] = arr[start];//记录当前数据
	printAllCombination(arr,data ,start+1, end, index+1, r);
	printAllCombination(arr, data,start+1, end, index, r);
}
int main() {
	int arr[] = {1,2,3,4,5};
	int r = 3;
	int * data = new int[r];//保持组合数
	printAllCombination(arr,data, 0, sizeof(arr)/sizeof(arr[0]) ,0 ,r);
	return 0;
}
