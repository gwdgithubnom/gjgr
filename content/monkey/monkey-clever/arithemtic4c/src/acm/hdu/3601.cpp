#include<stdio.h>
#include<string.h>
#include<algorithm>
using namespace std;
int sub(int n){
    int s=1;
    if(n/2>1)
      s=sub(n/2)+1;
    return s;
}

int main(){
    char pre='\n',next='\n',now='\n';
    int sum=0;
    int state=0;
    char c='\n';
    int n=0;
    int m=0;
    while(scanf("%c",&c),c!='\n'){
        next=c;
        if(pre==next){
            if(state==0){
                now=pre;
            }
            state++;
            sum++;
        }else{
              if(state>1){
                n=sub(state+1);
                m+=(n+1)*n/2;
              }
            state=0;
        }
        pre=c;
    }
   printf("%d\n",m);
    if(state>1){
        n=sub(state+1);
        m+=(n+1)*n/2;
        state=0;
    }

    printf("%d",sum);
    return 0;
}
