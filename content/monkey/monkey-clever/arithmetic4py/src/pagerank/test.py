import numpy as np
import copy
n=6;
G=np.mat("0 0 0 1.0 0 1.0;1 0 0 0 0 0;0 1 0 0 0 0;0 1 1 0 0 0;0 0 1 0 0 0;0 0 0 1 1 0");
#0 0 0 1 0 1
#1 0 0 0 0 0
#0 1 0 0 0 0
#0 1 1 0 0 0
#0 0 1 0 0 0
#0 0 0 1 1 0
P=np.mat("0 0.0 0 0 0");
A=np.mat("0 0 0 1.0 0 1.0;1 0 0 0 0 0;0 1 0 0 0 0;0 1 1 0 0 0;0 0 1 0 0 0;0 0 0 1 1 0");

#for i in range(n):
#    len();
lj=[];
cj=[];
factor=0.85;
base=(1-factor)/n;
pagerank=np.zeros((n,1), dtype = float);
frank= np.zeros((n,1), dtype = float);
E=np.ones((n,n),dtype=float);
for i in range(n):
    count=0;
    for j in range(n):
        if A[i,j]>0:
            count=count+1;
    for j in range(n):
        if A[i,j]>0:
            A[i,j]=A[i,j]/count;
    cj.append(count);
    pagerank[i]=1.0;
min_delta=0.001
R=E;
A0=np.transpose(A);
#print(frank,pagerank)
# while max(abs(A-R))>0.001:
#     R=copy.deepcopy(A);
#     print(R)
flag=1;
while flag:
    frank=copy.deepcopy(pagerank);
    for i in range(n):
        for j in range(n):
            E[i][j]=1.0/n;
    pagerank=(factor*A0+(1-factor)*E)*frank;
    min=0.001;
    C=pagerank-frank;
    flag=0;
    for i in range(n):
        if(abs(C[i])>min):
            flag=1;
print(pagerank);