    #include <iostream>
    #include <Windows.h>
    using namespace std;

    #define  N 8       //���ǽ����8�ʺ����⣬���Ҫ��4��16�ʺ�ȣ�ֻ�뽫8�ĵ����ɣ�����ɡ�
    int queen[N]={0}; //����queen[N]���±��ʾ�ʺ����ڵ��У�queen[N]��ֵ��ʾ�ʺ����ڵ���

    //�жϻʺ��Ƿ��ܹ����ڵ�row��
    bool CanPlace(int row)
    {
            for(int j=0;j<row;j++)//����row�лʺ���ǰ�������еĻʺ���бȽ�
            {
                if (queen[row]==queen[j]||row+queen[row]==j+queen[j]||row-queen[row]==j-queen[j])
                    return false;//���ܷŻʺ�
            }
            return true; //���ԷŻʺ�

    }
    //����ʺ��λ��
    void PrintfQueen()
    {
        for(int i=0;i<N;i++)
        {
            for (int j=0;j<N;j++)
            {
                if(queen[i]==j)
                    cout<<"�� ";//����ʺ�
                else
                    cout<<"�� ";
            }
            cout<<endl;
        }
        cout<<endl<<endl;
    }

    int Sum=0;
    void Queen(int row)
    {
        if (row>=N)//�ʺ��Ѿ�ȫ������ʱ
        {
            PrintfQueen();//����ʺ��λ��
            Sum++;
            return;
        }
        else
        {
            for (int i=0;i<N;i++) //�ܹ���N�У�һ��һ�е���̽���ûʺ�
            {
                queen[row]=i; //����row�лʺ���ڵ�i������

                if(CanPlace(row))//�жϻʺ��λ���Ƿ���ȷ
                    Queen(row+1);  //����һ���ʺ�
            }
        }
    }


    void main(void)
    {
        Queen(0);
        cout<<"�ܵİڷ�,Sum="<<Sum;
        cin.get();
    }
