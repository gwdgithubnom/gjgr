    #include<iostream>
    #include<cmath>
    #include<algorithm>
    #include<vector>
    #include<cstdio>
    #include<utility>
    using namespace std;
    typedef pair<double,double>P;
    #define maxn 5005
    #define INF 1e8
    #define eps 0.00001
    P p[maxn];

    bool comp_y(P a,P b){
        return a.second-b.second<eps;
    }
    double closest(P *p,int n){
        if(n<=1)return INF;
        int m=n/2;
        double x=p[m].first;
        double d=min(closest(p,m),closest(p+m,n-m));//二分
        inplace_merge(p,p+m,p+n,comp_y);
        vector<P>b;
        //for核心语句
        for(int i=0;i<n;i++){
            if(fabs(p[i].first-x)>=d)continue;
            for(int j=0;j<b.size();j++){
                double dx=p[i].first-b[b.size()-j-1].first;
                double dy=p[i].second-b[b.size()-j-1].second;
                if(dy>=d)break;
                d=min(d,sqrt(dx*dx+dy*dy));
            }
            b.push_back(p[i]);
        }
        return d;
    }
    int main(){
        int n;
        scanf("%d",&n);
        int i=0;
        while(i<n){
            scanf("%lf%lf",&p[i].first,&p[i].second);
            i++;
        }
        sort(p,p+n);
        printf("%.2lf\n",closest(p,n));
    }
