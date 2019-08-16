#include<stdio.h>
#include<string.h>
#include<math.h>
#include<algorithm>
using namespace std;
int comp(int n){
    if(n<2)return 0;
    if(n%2==0)return 0;
    for(int i=3;i<=sqrt(n);i+=2) if(n%i==0) return 0;
    return 1;
}
int main(){
    int a[1001];
    int i=0,j=0;
    int n=0,m=0;
    int sum=0;
    for(i=1;i<1001;i++){
        a[i]=comp(i);
    }
    while(scanf("%d",&n)!=EOF){
        sum=0;
        m=ceil(sqrt(n))+1;
        for(i=2;i<=m;i++){
            if(a[i]&&a[n-i]){
                sum++;
            }
        }
        printf("%d",sum);
    }
}
