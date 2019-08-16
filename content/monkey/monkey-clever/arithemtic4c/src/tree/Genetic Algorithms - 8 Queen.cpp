    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>
    #include <time.h>
    #define N 16//��ĸ�����Ĵ���

    char *crossover(char* father,char* mother)//���溯��������ֵΪ�������Ӵ�,����ԭ�������������������ĵ�������ϸ˵��
    {
        int i,j,k;
        char son[16],rnd[16],fath[16],moth[16];
        for ( i = 0; i < 8; i++)//���ɽ�������
        {
            rnd[i]=rand()%2+'0';
        }
        strcpy(fath,father);
        strcpy(moth,mother);

        for ( i = 0; i < 8; i ++)//���ݽ������ӶԸ�ĸ���н���
        {
            if (rnd[i] == '1')
            {
                for ( j = 0; j < 8; j++)
                {
                    if (fath[j] != '0')
                    {
                        break;
                    }
                }
                son[i]=fath[j];
                for ( k = 0; k < 8; k++)
                {
                    if (moth[k] == fath[j])
                    {
                        moth[k]='0';
                        break;
                    }
                }
                fath[j]='0';
            }
            else
            {
                for ( j = 0; j < 8; j++)
                {
                    if (moth[j] != '0')
                    {
                        break;
                    }
                }
                son[i]=moth[j];
                for ( k = 0; k < 8; k++)
                {
                    if (fath[k] == moth[j])
                    {
                        fath[k]='0';
                        break;
                    }
                }
                moth[j]='0';
            }
        }
        son[8]=0;
        return son;
    }

    int check(char result[])//����ж�����һ�����ϵĻʺ�
    {
        int i,j,k=0;
        for ( i = 0; i < 8; i++)
        {
            for ( j = i+1; j < 8; j++)
            {
                if (abs(result[i]-result[j]) == j-i)
                {
                    k++;
                }
            }
        }
        return k;
    }

    int main()
    {
        int i,j,k,p,q,min,max=0,flag,sign,boundary=1000,s[N];
        char father[16]="12345678",mother[16]="87654321";
        char son[N][16],temp[16],result[100][16];
        srand((unsigned)time(NULL));
        for ( i = 0; i < 8; i++)//���ɸ�ĸ��
        {
            j=rand()%8;
            k=father[j];
            father[j]=father[i];
            father[i]=k;
            j=rand()%8;
            k=mother[j];
            mother[j]=mother[i];
            mother[i]=k;
        }
        sign=0;
        for ( i = 0; i <= boundary; i++)//���Ʒ�ֳ����
        {
            if (i == boundary&&sign == 1)//��̬���ƣ�������ֲ���������ֳ3000��
            {
                boundary+=3000;
                sign=0;
            }
            for ( j = 0; j < N; j++)//����ͱ��죬���Ӵ�����
            {
                strcpy(son[j],crossover(mother,father));
                p=rand()%8;
                q=rand()%8;
                k=son[j][p];
                son[j][p]=son[j][q];
                son[j][q]=k;
                s[j]=check(son[j]);
            }
            for ( j = 0; j < N; j++)//������������򣬽��Ѿ���������������
            {
                for ( k = j, min = j; k < N; k++)
                {
                    if (s[min] > s[k])
                    {
                        min=k;
                    }
                }
                k=s[j];
                s[j]=s[min];
                s[min]=k;
                strcpy(temp,son[j]);
                strcpy(son[j],son[min]);
                strcpy(son[min],temp);
                if (s[j] == 0)
                {
                    flag=0;
                    for ( k = 0; k < max; k++)
                    {
                        if (!strcmp(son[j],result[k]))
                        {
                            flag=1;
                        }
                    }
                    if (!flag)
                    {
                        sign=1;
                        strcpy(result[max],son[j]);//���ϵķ���������
                        max++;//�Ѿ������ķ����������Ӵ�
                        printf("%02d.%s\n",max,son[j]);
                    }
                }
            }
            strcpy(father,son[0]);//ѡ���������ŵĳ�Ϊ��һ���ĸ�ĸ
            strcpy(mother,son[1]);
        }
        printf("%d\n",boundary);//���շ�ֳ����
        system("pause");
        return 0;
    }
