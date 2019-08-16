#include"stdio.h"
#include"string.h"
int main(){
	int t;
	double p,n;
	double r;
	printf("this is qq3");
	scanf("%d",&t);
	while(t--){
		r=1;
		scanf("%d%d",&p,&n);
		while(n--){
			r=(1-p)*r+r*p;
		}
		printf("%3f\n",r);
	}
	return 0;
}
