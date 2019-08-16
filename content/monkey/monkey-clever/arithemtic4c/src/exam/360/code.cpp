#include"stdio.h"
#include"string.h" 
#define N 1000000
int fun(char a[],char b[],char c[]){
 	int len1=strlen(a);
 	int len2=strlen(b);
 	int len3=strlen(c);
 	int i=len3;
    int sum=0;
 	char *p=a;
	char *q=b;
	char *r=&c[len3-1];
	/*
	* this is to test it if forward.
	*/	
	while(*p!='\0'&&(*q!='\0'||*r!='\0')){
 			
	 		if(*p==*q){
		 		q++;
		 	}
			if(*p==*r){
	 			r--;
	 			i--;
	 		}
	 			p++;	
	 	}
	 	
	 	if(*q=='\0'&&*r==0)
	 	 sum=1;//forward
		/*
		*this is use to make sure it could back
		*/ 	
	 	p=&a[len1-1];
	 	q=&b[len2-1];
	 	i=len2;
	 	r=c;
	 	int flag=0;
	 	while(*p!='\0'&&(*q!='\0'||*r!='\0')){	 		
	 		if(*p==*q){
		 		q--;
		 		i--;
		 	}
 	        if(*p==*r&&flag==1){
	 			r++;
	 		}
	 		flag=1;
	 		p--;	
	 	}
	 	
	 	if(*r==0&&*q==0)
	 	 sum=sum+2;
    return sum;
 } 
 
 int main(){
 	char a[N],b[N],c[N];
 	int s=0;
 	while(scanf("%s%s%s",&a,&b,&c)!=EOF){
	 	s=0;
		s=fun(a,b,c);		
		//printf("****%d****\n",s);
		/*
		* this is may the s=0 is always to happen so it use first.
		*/
	 	if(s==0)
	 	printf("invalid\n");
	 	else if(s==1)
	 	printf("forward\n");
	 	else if(s==2)
	 	printf("backward\n");
	 	else if(s==3)
	 	printf("both\n");
	 	/*
	 	* I did not make sure of it if it is right.
	 	*/
	 }
 	return 0;
 }
