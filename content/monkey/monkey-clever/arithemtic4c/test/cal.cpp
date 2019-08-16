/*
 * cal.cpp
 *
 *  Created on: Oct 11, 2016
 *      Author: gwd
 */


#include"stdio.h"
int main(){
	int i,j,k,l,m,n;
	int sum=0,count=0;
	for(k=8;k<=30;k++){
		for(l=-5;l<=30;l++){
			for(m=0;m<=30;m++){
				for(n=2;n<=30;n++){
					sum=k+l+m+n;
					if(sum==30)
						count++;
				}
			}
		}
	}
	printf("%d",count);
	return 0;
}

