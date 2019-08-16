int total;
int pre[1001];
void init(int n){
    int i;
    for(i=1;i<=n;++i){
        pre[i] = i;
    }
}

int root(int x){
    if(x!=pre[x]){
        pre[x] = root(pre[x]);    }
    return pre[x];
}

void merge(int a,int b){
    int x = root(a);
    int y = root(b);
    if(x!=y){
        pre[x]=y;
        --total;
    }
}

int main(){
    int N,M,i,st,end;
    while(printf("请输入城镇数目N ( < 1000 )和道路数目M.\n"),scanf("%d",&N) && N){
        scanf("%d",&M);
        init(N);
        total = N-1;
        printf("现已有道路直接连通的两个城镇的编号.\n");
        for(i=0;i<M;++i){
            scanf("%d %d",&st,&end);
            merge(st,end);
        }
        printf("还需要修建：%d条道路\n",total);
    }
    return 0;
}
