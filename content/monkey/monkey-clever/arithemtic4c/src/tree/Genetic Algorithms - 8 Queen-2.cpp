    #include <iostream>
    #include <Windows.h>
    using namespace std;

    #define  N 6       //这是解的是8皇后问题，如果要解4、16皇后等，只须将8改掉即可，方便吧。
    int queen[N]={0}; //数组queen[N]的下标表示皇后所在的行，queen[N]其值表示皇后所在的列

    //判断皇后是否能够放在第row行
    bool CanPlace(int row)
    {
            for(int j=0;j<row;j++)//将第row行皇后与前面所有行的皇后进行比较
            {
                if (queen[row]==queen[j]||row+queen[row]==j+queen[j]||row-queen[row]==j-queen[j])
                    return false;//不能放皇后
            }
            return true; //可以放皇后

    }
    //输出皇后的位置
    void PrintfQueen()
    {
        for(int i=0;i<N;i++)
        {
            for (int j=0;j<N;j++)
            {
                if(queen[i]==j)
                    cout<<"■ ";//输出皇后
                else
                    cout<<"□ ";
            }
            cout<<endl;
        }
        cout<<endl<<endl;
    }

    int Sum=0;
    void Queen(int row)
    {
        if (row>=N)//皇后已经全部放完时
        {
            PrintfQueen();//输出皇后的位置
            Sum++;
            return;
        }
        else
        {
            for (int i=0;i<N;i++) //总共有N列，一列一列的试探放置皇后
            {
                queen[row]=i; //将第row行皇后放在第i列上面

                if(CanPlace(row))//判断皇后的位置是否正确
                    Queen(row+1);  //放下一个皇后
            }
        }
    }


int main()
    {
        Queen(0);
        cout<<"总的摆法,Sum="<<Sum;
        cin.get();
        return 0;
    }
