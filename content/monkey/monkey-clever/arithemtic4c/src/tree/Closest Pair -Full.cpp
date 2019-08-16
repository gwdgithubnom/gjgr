#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define NUM 1000 // 二维坐标中点的数目
// 代表点的结构体
typedef struct{
   int x;
   int y;
}N;
double  distance(N n1,N n2);
double minDis(double d1,double d2);
double  shortestDis(N *data,int length,N *n1 , N *n2);
void  MergeSort(N q[],int num,int mode);
double  shortestDis(N *data,int length,N *n1 , N *n2){
    int pre,last,middle,median;
    int i,c1num = 0,c2num = 0,j;
    N* dataP;
    N* dataL;
    N* CP;
    N* CL;
    N tn1,tn2;
    double dis1 ,dis2;
    // 当只有两个点时，返回最短距离，和点
    if(length  ==  2 ){
      double dis1 = distance(data[0],data[1]);
       *n1 = data[0];
       *n2 = data[1];
       return  dis1;
    }else if(length == 3){
    // 当只有三个点时，返回最短距离，和点
     double dis1 = distance(data[0],data[1]);
     double dis2 = distance(data[1],data[2]);
     double dis3 = distance(data[0],data[2]);
     double temp;
     temp =  dis1 < dis2 ? dis1:dis2;
     temp = temp < dis3 ? temp : dis3;
     if(temp == dis1){
       *n1 = data[0];*n2 = data[1];
     }else if(temp == dis2){
       *n1 = data[1];*n2 = data[2];
     }else{
       *n1 = data[0];*n2 = data[2];
     }
     return temp;
    }
     middle =length/2;
     pre = middle;
     last  = length - pre;
     median = data[middle].x; // 记录中位数
     dataP = (N*)malloc(sizeof(N)*pre);
     dataL = (N*)malloc(sizeof(N)*last);
     CP = (N*)malloc(sizeof(N)*pre);
     CL = (N*)malloc(sizeof(N)*last);
    for( i  = 0;i < pre ;i++)
      dataP[i] = data[i];
    for( i = 0; i< last;i++)
     dataL[i] = data[i+pre];
     dis1 = shortestDis(dataP , pre , n1 , n2);
     dis2 = shortestDis(dataL , last , &tn1 , &tn2);
     if(dis1 > dis2){
       *n1 = tn1;
       *n2 = tn2;
     }
     dis1 = minDis(dis1,dis2);

     for( i = 0; i < pre ; i++)
     if(dataP[i].x - median < dis1){
        CP[c1num++] =  dataP[i];
     } // 将在中位数之前的区域中与中位数距离小于 最短距离的点放到 CP 中
     for( i = 0; i < last ; i++)
     if(median - dataL[i].x < dis1){
        CL[c2num++] =  dataL[i];
     }// 将在中位数之后的区域中与中位数距离小于 最短距离的点放到 CL 中
     for(i = 0; i< c1num;i++){
          for( j =0; j < c2num ; j++){
              double  temp = distance(CP[i],CL[j]);
              if(temp < dis1){
                dis1 = temp;
                *n1 = CP[i];
                *n2 = CL[j];
              }
          }

     }//依次计算中位数两旁的区域中，每一个点与另外一个区域中的距离，并且记录最短距离
    return  dis1;
}
double  distance(N n1,N n2){
   return  sqrt((n1.x -n2.x)*(n1.x -n2.x) + (n1.y - n2.y)*(n1.y - n2.y));
}
double minDis(double d1,double d2){
    double d =  d1 < d2 ? d1 : d2;
  return  d;
}
// 分治排序的合并算法
void Merge(N *pre,N *last,N *total,int nump,int numl,int mode){
    int i = 0,j = 0,k = 0;
    while( i< nump && j< numl ){
      if(mode == 0){
       if(pre[i].x > last[j].x ){
           total[k++] = pre[i++];
      }else{
           total[k++] = last[j++];
      }
    }else{
       if(pre[i].y > last[j].y ){
           total[k++] = pre[i++];
      }else{
           total[k++] = last[j++];
      }
    }
    }
    if(i  == nump){
     for(i = j; i < numl; i++)
       total[k++] = last[i];
    }else{
      for(j = i; j < nump; j++)
       total[k++] = pre[j];
    }

}
// 蛮力法计算最近点对
void  computeShortestDistance(N* data , int num ,int result[4]){
      FILE *fo;
      int i,j,l = 0;
      int *datax,*datay;
      double dis = 666666,temp;
     datax = (int*)malloc(sizeof(int)*1000);
     datay = (int*)malloc(sizeof(int)*1000);
     for(i  =0; i<num ;i++){
       datax[i] = data[i].x;
       datay[i] = data[i].y;
     }

     for(i = 0;i<num;i++){

     for(j = i+1;j<num;j++)
        if((temp = (datax[i] - datax[j])*(datax[i] - datax[j]) + (datay[i] - datay[j])*(datay[i] - datay[j])) < dis){
            dis = temp;
            result[0] = datax[i];
            result[1] = datay[i];
            result[2] = datax[j];
            result[3] = datay[j];
        }
    }
   printf("\n蛮力法:\n");
   printf("shortest dis: %f",sqrt(dis));

}
// 生成随即点
void  generateDots(int number){
    FILE *fo;
    int i,n1,n2;
    if(!(fo = fopen("data.txt","w"))){
      printf("open file fail");
      exit(1);
   }
   for(i = 0;i< number;i++){
      srand((i*i));
      n1 =rand()%8000;
     // srand(time(NULL)*i*i);
      n2 = rand()%6000;
      if(i%2)
      fprintf(fo,"%d %d\n",n1,n2);
      else
      fprintf(fo,"%d %d\n",n2,n1);
   }
   fclose(fo);
}
// 分治法排序 mode = 0 按照 X ，升序排序，mode = 1 按照 Y 排序
void  MergeSort(N q[],int num,int mode){
     int i,nump,numl;
     N* qPre;
     N* qLast;
     if(num == 1 )
        return;
     if(num%2&&num != 2){
        numl = num/2;
        nump = num/2;
       nump++;
     }else{
        numl = num/2;
        nump = num/2;
     }
     qPre  = (N*)malloc(sizeof(N)*nump);
     qLast = (N*)malloc(sizeof(N)*numl);
     for(i = 0;i < nump;i++)
       qPre[i] = q[i];
     for(i = 0;i<numl;i++)
      qLast[i] = q[nump+i];
     MergeSort(qPre,nump,mode);
     MergeSort(qLast,numl,mode);
     Merge(qPre,qLast,q,nump,numl,mode);
}

int main()
{   FILE* fo;
    N* data;
    int i;
    N n1,n2;
    double dis;
    int re[4];
    // 生成数据
    generateDots(NUM);
    data  = (N*)malloc(sizeof(N)*1000);
     if(!(fo = fopen("data.txt","r"))){
       printf("open file fail");
       exit(1);
     }
   for(i = 0;i < NUM;i++){
     fscanf(fo,"%d %d",&data[i].x,&data[i].y);
   }
   fclose(fo);
   // 合并排序，排好序的数据放置到 data 中。
   MergeSort(data,NUM,0);
   // 分治法
   dis = shortestDis(data,NUM,&n1,&n2);
   printf("分治法:\n");
   printf("%f\nX:%d\tY:%d\nX:%d\tY:%d\t",dis,n1.x,n1.y,n2.x,n2.y);
   //  蛮力法
   computeShortestDistance(data,NUM,re);
   printf("\nx:%d\ty:%d\nx:%d\ty:%d",re[0],re[1],re[2],re[3]);
}

