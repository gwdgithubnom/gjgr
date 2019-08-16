    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>
    #include <time.h>
    #define N 16//父母产生的代数

    char *crossover(char* father,char* mother)//交叉函数，返回值为交叉后的子代,具体原理不在这里阐述，在设计文档中有详细说明
    {
        int i,j,k;
        char son[16],rnd[16],fath[16],moth[16];
        for ( i = 0; i < 8; i++)//生成交叉算子
        {
            rnd[i]=rand()%2+'0';
        }
        strcpy(fath,father);
        strcpy(moth,mother);

        for ( i = 0; i < 8; i ++)//根据交叉算子对父母进行交叉
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

    int check(char result[])//检查有多少在一条线上的皇后
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
        for ( i = 0; i < 8; i++)//生成父母串
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
        for ( i = 0; i <= boundary; i++)//控制繁殖代数
        {
            if (i == boundary&&sign == 1)//动态控制，如果发现不够继续繁殖3000代
            {
                boundary+=3000;
                sign=0;
            }
            for ( j = 0; j < N; j++)//交叉和变异，对子代评估
            {
                strcpy(son[j],crossover(mother,father));
                p=rand()%8;
                q=rand()%8;
                k=son[j][p];
                son[j][p]=son[j][q];
                son[j][q]=k;
                s[j]=check(son[j]);
            }
            for ( j = 0; j < N; j++)//对评估结果排序，将已经符合条件的挑出
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
                        strcpy(result[max],son[j]);//符合的放入结果数组
                        max++;//已经产生的符合条件的子代
                        printf("%02d.%s\n",max,son[j]);
                    }
                }
            }
            strcpy(father,son[0]);//选择两个最优的成为下一代的父母
            strcpy(mother,son[1]);
        }
        printf("%d\n",boundary);//最终繁殖代数
        system("pause");
        return 0;
    }
