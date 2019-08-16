#include <stdio.h>
#include <stdlib.h>
//#define maxN 10
#define maxN 4

void permutation(int n,int arry[],int cur)
{
        if(cur == n)
        {
                int i=0;
                for(; i<n; ++i)
                {
                        printf("%d\t",arry[i]);
                }
                printf("\n");
                return;
        }
        int iwhich=1;
        for(;iwhich<=n;++iwhich)
        {
                bool bInArry=false;
                int itmp=0;
                for(;itmp<cur;++itmp)
                {
                        if(iwhich == arry[itmp])
                        {
                                bInArry = true;
                                break;
                        }
                }//is in?
                if(false == bInArry)
                {
                        arry[cur] = iwhich;
                        permutation(n,arry,cur+1);
                }
        }
}

int main()
{
        int arry[maxN] = {-1};
        permutation(maxN,arry,0);
        return 0;
}
