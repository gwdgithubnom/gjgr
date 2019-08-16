#include<stdio.h>
#include<string.h>
#include<algorithm>
using namespace std;
int main(){
	char str[111111];
	while(scanf("%s",str)!=EOF){
		int sum1 =0,cou1 = 0;
		int sum2 = 0,cou2 = 0;
		int i;
		int len = strlen(str);
	    for(i=0;i<len;i++){
    		if(str[i]=='G') cou1++;
   			if(str[i]=='B') sum1+=cou1;
   			if(str[i]=='B') cou2++;
   			if(str[i]=='G') sum2+=cou2;
    	}
    	printf("%d\n",min(sum1,sum2));
	}
	return 0;
}
