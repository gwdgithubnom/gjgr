#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>
/*------------------定义结构体变量--------------------------*/
typedef struct 
{
    int husb;  //男人人数
    int wife;   //女人个数
    int cw;   //船的位置
}DataType;


/*-------定义结点结构体变量*/
typedef struct node//结构体定义
{
    DataType data;
    struct node *son;//儿子
    struct node *bro;//兄弟
    struct node *par;//双亲
    struct node *next;
}Link;



DataType array[50000];
/*----------------初始化函数--------------------------------*/
void Linkinit(Link **head)    
{
    *head=(Link *)malloc(sizeof (Link));  
    (*head)->son=NULL;
    (*head)->bro=NULL;
    (*head)->par=NULL;
    (*head)->next=NULL;
}

void insertson(Link *head, DataType x)   //在邻接表中插入儿子结点的操作
{
    Link *q,*s;
    q=(Link *)malloc(sizeof (Link));
    q->data=x;
    head->son=q;//将x插入给头结点的儿子指针
    s=head;
    while (s->next!=NULL)
    s=s->next;
    q->par=head;
    q->son=NULL;
    q->bro=NULL;
    s->next=q;
    q->next=NULL;
}

void insertbro(Link *head,DataType x)//在邻接表中插入兄弟结点的操作，
                                       //所有的兄弟结点都指向他们右边的结点
{
    Link *q,*s;
    q=(Link *)malloc(sizeof (Link));
    s=head->son;
    q->data=x;
    while (s->bro!=NULL)
       s=s->bro;
    s->bro=q;
    s->next=q;
    q->next=NULL;
    q->bro=NULL;
    q->par=head;
    q->son=NULL;
}

 

int boatcase(DataType x,int n) //生成所有情况；
{
    int i=0,a,b;
    if(x.cw)   //船在起始点开往目的地,上船的人多优先
    {
       a=0;b=n-a; //a为男人b为女人
       while (a+b>=1)//当船上有人时
           {
             
              while (b>=0)//当女人个数不为负数
              {                   
                  array[i].husb=a;
                  array[i].wife=b;
                  i++;
                  a++;
                  b--;
              }
              a=0;
             b=0; 
           }
    }
    else//船在目的地开往起始点,上船的人少优先
    {
       a=1;b=0;
       while (a+b<=n)
       {
           
           while (a>=0)
           {                
              array[i].husb=a*(-1);
              array[i].wife=b*(-1);
              i++;
              a--;
              b++;
           } 
           a=n;
           b=n;
       }
    }
    return i;  //i为总数量
}
/*-----------------------------条件限制检测函数------------------------------------------------------------*/
int safe(DataType x,int n)
{ 
    if ((x.husb>=x.wife||x.husb==0)&&((n-x.husb)>=(n-x.wife)||x.husb==n)&&x.husb>=0&&x.husb<=n&&x.wife>=0&&x.wife<=n)
       return 1;
    else
       return 0;
}
/*---------------------------------结果输出函数----------------------------------------------------------------*/
void print(Link *q,Link *p)     //打印安全渡河的过程,当船到对岸时,把对岸当作其始岸,此岸当作彼岸
{
    DataType a[100];
    int i=1;
    a[0].cw=0;
    a[0].husb=0;
    a[0].wife=0;
    while (q!=p)//避免出现相同情况而循环
    {
       a[i++]=q->data;//将一次过河的情况给b[i]
       q=q->par;
    }
    while ((--i)>-1)   //输出过河图  
    {
          printf("( %d %d %d )",a[i].husb,a[i].wife,a[i].cw);
          if (!(a[i].husb==0&&a[i].wife==0&&a[i].cw==0)) 
          {
		   if(a[i].cw==1)           
			   printf("-->(%d %d)-->(%d %d 0)\n",a[i].husb-a[i-1].husb,a[i].wife-a[i-1].wife,a[i-1].husb,a[i-1].wife);
              //a[i].husb-a[i-1].xds表示过河过程中船上的男人数,a[i].wife-a[i-1].yr表示过河过程中船上的女人人数
		   else
			  printf(" <-- ( %d %d ) <-- ( %d %d 1 )\n",(a[i].husb-a[i-1].husb)*(-1),(-1)*(a[i].wife-a[i-1].wife),a[i-1].husb,a[i-1].wife);
          }
    }
    printf("渡河成功!\n");
}

 
/*--------------------进行广度搜索查找解-------------------------*/
void BFS(Link *p,int n,int c)   
{
       Link *q,*t;
       DataType tem;
       int i,flag1,flag2,g=0,j;
	   int count=0; //标记解的个数
       q=p->son;
       while (q!=NULL) 
       {
        flag1=0;        
		j=boatcase(q->data,c);//可能过河的情况
        for (i=0;i<j;i++)//搜索兄弟结点
       {
                tem.husb=q->data.husb-array[i].husb;
                tem.wife=q->data.wife-array[i].wife;
                tem.cw=1-q->data.cw;
                t=q;
                if (safe(tem,n))//是否安全
                {
                  flag2=1;//1表示没有死循环
                  while (t!=p)//保证不会出现循环
                  {
                    if(tem.husb== t->data.husb&&tem.wife==t->data.wife&&tem.cw==t->data.cw)
                    {//出现相当情况时候
                     flag2=0;
                     break;                
                    }
                    t=t->par;
                  }
                if(flag2==1)
                 {
                    if (flag1==0)                    
					{
                      insertson(q, tem);
                      flag1=1;
                    }
                 else 
                 insertbro(q,tem);                                                     
                 if (tem.husb==0&&tem.wife==0&&tem.cw==0)  //当检测到这个状态时，说明问题得到正确解
                 {
                   print(q,p);
                   count++;
                 }
				}                   
        } 
       }    
         q=q->next;
       }  
       if (count==0)
              printf("无法成功渡河!\n");
       else
              printf("有%d种渡河方式。\n",count);
}
 
/*---------------------------------主函数------------------------------------------------------------*/

int main()
{
    int n,c,back; //n表示夫妻对数，c表示船的负载能力 
	Link *p;
    DataType tem;
   
	while (back)
    {
       printf("请输入夫妻对数n:\n");
       scanf("%d",&n);
       if (n==0)
           break;
       printf("请输入船可容纳的人数c:\n");
       scanf("%d",&c);
      
	   tem.husb=n;       //定义初始状态
       tem.wife=n;
       tem.cw=1;
      
	   Linkinit(&p);			//初始化邻接表;
       insertson(p, tem);		//将初始状态作为头结点的孩子结点；
	   BFS(p,n,c);				//进行广度搜索；
      
	   
	   printf("是否继续?(继续 1 ,退出 0 )\n");
	   scanf("%d",&back);
	   
	}
}
