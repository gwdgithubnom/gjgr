#include<stdio.h>
#include<string.h>
#include<algorithm>
using namespace std;
int main(){
    float a[101];
    int x[101];
    int n;
    float sum=0;
    int temp=0;
    int i=0;
    scanf("%d",&n);
    while(n>i){
        scanf("%d%d",&x[i],&temp);
        a[i]=temp/100.0;

        i++;
    }
    for(i=0;i<n;i++){

        sum+=a[i]*x[i];

    }
    printf("%.3f",sum);
    return 0;
}
