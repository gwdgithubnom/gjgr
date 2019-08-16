#include<stdio.h>
void Arrange(int cur,int n,int* arr){
    if(cur==n+1){
        for(int i=1;i<cur;i++)
            printf("%d ",arr[i]);
        printf("\n");
        return ;
    }
    for(int i=1;i<=n;i++){
        int ok=1;
        for(int j=1;j<cur;j++)
            if(arr[j]==i)
                ok=0;
        if(ok){
            arr[cur]=i;
            Arrange(cur+1,n,arr);
        }
    }
}
int main(){
    int arr[15];
    //生成1~n的排列
    int n;
    printf("Input n:");
    scanf("%d",&n);
    Arrange(1,n,arr);
    return 0;
}
