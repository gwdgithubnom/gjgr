/*
3 2
1 2 3
8 9 7
*/
#include<stdio.h>
#include<string.h>
#include<algorithm>
using namespace std;
#define SMod 100
struct Matrix{
    int m[60][60];
};
int n, k,num[1111];
Matrix Mul(Matrix a,Matrix b,int m){
    Matrix c;
    memset(c.m,0,sizeof(c.m));
    for(int i=0;i<m;i++)
        for(int j=0;j<m;j++)
            for(int k=0;k<m;k++)
                c.m[i][j] += ((a.m[i][k]*b.m[k][j])%SMod + SMod)%SMod;
    return c;
}

Matrix fastm(Matrix a,int n,int m){

    Matrix res;
    memset(res.m,0,sizeof(res.m));
    for(int i = 0;i<m;i++){
    	res.m[i][i] = 1;
    }
    while(n){
        if(n&1)
            res = Mul(res,a,m);
        n>>=1;
        a = Mul(a,a,m);
    }
    return res;
}



int main(){
	while(scanf("%d %d",&n,&k)!=EOF){
	  for(int i=0;i<n;i++){
  		 scanf("%d",&num[i]);
       }
       struct Matrix  m1;
       memset(m1.m,0,sizeof(m1.m));
       int cou = 0;
       for(int i=0;i<n;i++){
       	    m1.m[cou][i] = 1;
       	    m1.m[cou][(i+1)%n] = 1;
       	    cou++;

       }

     struct Matrix m2 = fastm(m1,k,n) ;
     int i,j;

     for(int j = 0;j<n;j++){
     	int sum = 0;

       for(int i = 0;i<n;i++){
     	sum += m2.m[j][i]*num[i];
       }
        printf("%d ",sum%100);
     }
     printf("\n");

	}
	return 0;
}
