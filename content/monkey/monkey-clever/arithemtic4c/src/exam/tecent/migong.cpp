/*
 * migong.cpp
 *
 *  Created on: Oct 11, 2016
 *      Author: gwd
 */

#include"stdio.h"
#include"string.h"
#define N 1000
int map[1000][1000];
int main(){
	int n=0;
	int m=0;
	int count=0;
	int i,j,k,l;
	memset(map,0,1000*1000*sizeof(int));
	scanf("%d",&n);
	for(i=0;i<n;i++){
		for(j=0;j<i;j++){
			map[i][j]=i+j;
		}
	}
	for(i=0;i<n;i++){
		for(j=0;j<i;j++){
			if(map[i][j]>=map[i][j-1])
				count++;
		}
	}

	printf("%d",count);
	for(i=0;i<n;i++){
		for(j=0;j<i;j++){
			if(map[i][j]>=map[i][j-1])
				count++;
		}
	}
	return 0;
}


