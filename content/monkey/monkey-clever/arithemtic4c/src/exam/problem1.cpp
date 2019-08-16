/*
枚举法求解
先算一个走路出去的时间，对于其他方案必然是先去找出租车然后去公司，就两种方法

2
-2 -2
0 -2

-4 -2
15 3

42
*/
#include<stdio.h>
#include<string.h>
#include<algorithm>
using namespace std;
int toint(int i){
    if(i>0)
        return i;
    else
        return -i;
}

int main(){
    int n;
    int carX[51],carY[51],jarge[51];
    int car,people;
    int gx,gy;
    int m=0;
    int temp=0;
    int time=0;
    scanf("%d",&n);
    int i=0,j=0;
    while(n>i){
        scanf("%d",&carX[i]);
        i++;
    }
    while(n>j){
        scanf("%d",&carY[j]);
        j++;
    }
    scanf("%d%d",&gx,&gy);
    scanf("%d%d",&car,&people);
    temp=toint(carX[0])+toint(carY[0]);
    for(i=1;i<n;i++){
        jarge[i]=toint(carX[i])+toint(carY[i]);
        if(jarge[i]<temp)
            m=i;
    }
    int time=0;
    if((gx+gy)>jarge[m]){
        time=jarge[m]*people;
        time=time+(toint((gx-jarge[m]))+toint(gy-jarge[m]))*car;
    }else{
        time=(toint(gx)+toint(gy))*people;
    }
    printf("%d",time);
    return 0;
}
