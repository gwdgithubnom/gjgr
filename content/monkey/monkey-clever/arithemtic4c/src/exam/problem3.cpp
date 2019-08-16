/*
3 2
1 2 3
8 9 7
*/
#include<stdio.h>
#include<string.h>
#include<algorithm>
using namespace std;
int main(){
    int n=0,k=0;
    int a[51];
    int i,j;
    scanf("%d%d",&n,&k);
    i=1;
    while(n>=i){
      scanf("%d",&a[i]);
      i++;
    }
    for(i=0;i<k;i++){
        a[0]=a[1];
        for(j=1;j<n;j++){
            a[j]=a[j]+a[j+1];
            a[j]%=100;
        }
        a[j]=a[j]+a[0];
        a[j]%=100;
    }
    for(i=1;i<n;i++){
        printf("%d ",a[i]);
    }
    printf("%d",a[i]);
    return 0;
}
