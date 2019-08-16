#include<iostream>
#include<cstring>
using namespace std;

const int n = 8;
int C[8];       //状态空间,C[i]表示第i行的皇后放在第C[i]列；
int fx[8][8];   //fx值，fx[i][j]表示在i,j处放置皇后后，剩下行中可以放Q的空格数
int ansflag = 0;//标记是否已经找到答案
bool vis[3][15];//vis[0][j]表示第j列有无皇后，vis[1 or 2][i+j]表示（i,j）正反对角线有无皇后

int f(int row)
{
    //找出剩下行可以放Q的空格数。
    int cnt = 0;
    for (int i = row+1; i < n; i++)
        for (int j = 0; j < n; j++)
            if (!vis[0][j] && !vis[1][i+j] && !vis[2][i-j+n])
                cnt++;
    return cnt;
}

void search(int cur)
{
    if (cur == n) ansflag++;    //所有行都合法地放置了皇后，结束
    else
    {
        int flag = 0;   //标记该行是否可以放置皇后
        for(int i = 0; i < n; i++)  //对cur行，测试每一个空格
        {
            if (!vis[0][i] && !vis[1][cur+i] && !vis[2][cur-i+n])//不与前面皇后冲突
            {
                flag = 1;   //该行可以放置皇后
                C[cur] = i; //尝试将皇后放在第i列
                vis[0][i] = vis[1][cur+i] = vis[2][cur-i+n] = 1;
                fx[cur][i] = f(cur);    //计算fx值
                vis[0][i] = vis[1][cur+i] = vis[2][cur-i+n] = 0;//计算完fx将皇后拿掉，尝试下一个格子
            }
        }
        if (flag)   //该行可以放置皇后，接下来尝试求解
        {
            while(!ansflag) //没有找到答案之前，进行回溯。
            {
                int max = -1;
                int col = -1;   //记录fx最大的列
                for (int i = 0; i < n; i++) //找fx最大的列
                {
                    if (fx[cur][i] > max)
                    {
                        max = fx[cur][i];
                        col = i;
                    }
                }
                if (max == -1)  //在本行任一空格放置皇后都无法求解，回溯
                {
                    fx[cur-1][C[cur-1]] = -1; //将原来的最大值置为-1，那么下一次回溯找的是次大值。
                    return;
                }
                C[cur] = col;   //找到fx最大的列，放置皇后，搜索下一行。
                vis[0][col] = vis[1][cur+col] = vis[2][cur-col+n] = 1;
                search(cur+1);
                vis[0][col] = vis[1][cur+col] = vis[2][cur-col+n] = 0;
            }
        }
        else    //如果该行无法放置皇后，将上一行中放置皇后的位置对于的fx置-1，回溯。
            fx[cur-1][C[cur-1]] = -1;
    }
}

int main()
{
    memset(C, -1, sizeof(C));
    memset(fx, -1, sizeof(fx));
    search(0);
    for (int i = 0; i < n; i++)
        cout<<"第"<<i<<"个皇后放置在第"<<i<<"行第"<<C[i]<<"列"<<endl;
    cout<<endl<<endl;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (j == C[i])
                cout<<"Q"<<' ';
            else
                cout<<"X"<<' ';
        }
        cout<<endl;
    }

    return 0;
}
