#include<iostream>
#include<cstring>
using namespace std;

const int n = 8;
int C[8];       //״̬�ռ�,C[i]��ʾ��i�еĻʺ���ڵ�C[i]�У�
int fx[8][8];   //fxֵ��fx[i][j]��ʾ��i,j�����ûʺ��ʣ�����п��Է�Q�Ŀո���
int ansflag = 0;//����Ƿ��Ѿ��ҵ���
bool vis[3][15];//vis[0][j]��ʾ��j�����޻ʺ�vis[1 or 2][i+j]��ʾ��i,j�������Խ������޻ʺ�

int f(int row)
{
    //�ҳ�ʣ���п��Է�Q�Ŀո�����
    int cnt = 0;
    for (int i = row+1; i < n; i++)
        for (int j = 0; j < n; j++)
            if (!vis[0][j] && !vis[1][i+j] && !vis[2][i-j+n])
                cnt++;
    return cnt;
}

void search(int cur)
{
    if (cur == n) ansflag++;    //�����ж��Ϸ��ط����˻ʺ󣬽���
    else
    {
        int flag = 0;   //��Ǹ����Ƿ���Է��ûʺ�
        for(int i = 0; i < n; i++)  //��cur�У�����ÿһ���ո�
        {
            if (!vis[0][i] && !vis[1][cur+i] && !vis[2][cur-i+n])//����ǰ��ʺ��ͻ
            {
                flag = 1;   //���п��Է��ûʺ�
                C[cur] = i; //���Խ��ʺ���ڵ�i��
                vis[0][i] = vis[1][cur+i] = vis[2][cur-i+n] = 1;
                fx[cur][i] = f(cur);    //����fxֵ
                vis[0][i] = vis[1][cur+i] = vis[2][cur-i+n] = 0;//������fx���ʺ��õ���������һ������
            }
        }
        if (flag)   //���п��Է��ûʺ󣬽������������
        {
            while(!ansflag) //û���ҵ���֮ǰ�����л��ݡ�
            {
                int max = -1;
                int col = -1;   //��¼fx������
                for (int i = 0; i < n; i++) //��fx������
                {
                    if (fx[cur][i] > max)
                    {
                        max = fx[cur][i];
                        col = i;
                    }
                }
                if (max == -1)  //�ڱ�����һ�ո���ûʺ��޷���⣬����
                {
                    fx[cur-1][C[cur-1]] = -1; //��ԭ�������ֵ��Ϊ-1����ô��һ�λ����ҵ��Ǵδ�ֵ��
                    return;
                }
                C[cur] = col;   //�ҵ�fx�����У����ûʺ�������һ�С�
                vis[0][col] = vis[1][cur+col] = vis[2][cur-col+n] = 1;
                search(cur+1);
                vis[0][col] = vis[1][cur+col] = vis[2][cur-col+n] = 0;
            }
        }
        else    //��������޷����ûʺ󣬽���һ���з��ûʺ��λ�ö��ڵ�fx��-1�����ݡ�
            fx[cur-1][C[cur-1]] = -1;
    }
}

int main()
{
    memset(C, -1, sizeof(C));
    memset(fx, -1, sizeof(fx));
    search(0);
    for (int i = 0; i < n; i++)
        cout<<"��"<<i<<"���ʺ�����ڵ�"<<i<<"�е�"<<C[i]<<"��"<<endl;
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
