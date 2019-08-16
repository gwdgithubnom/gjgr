__author__ = 'gwd'

from numpy import *
import matplotlib
import numpy as np
import matplotlib.pyplot as plt
x = random.rand(50,30)
# x=array([[0.8172221,0.02338412,0.13912367],[ 0.71760505,0.77381235,0.82113856]])
# print(x[:,0]);
# print(x[:,1]);
#print("x:",x);
#basic

f1 = plt.figure('title')
# plt.subplot(211)
# plt.scatter(x[:,1],x[:,0])
# with label
plt.subplot(222)
label = list(ones(20))+list(2*ones(15))+list(3*ones(15))
#print(label)
#print(list(ones(20)))
label = array(label)
print(label)
#plt.scatter(x[:,1],x[:,0],30.0*label,15.0*label)
plt.scatter([1,2,3,4,10,5,4],[2,3,4,5,7,8,99],[10,15,20,40,50,60,10],[67,68,69,70,71,72,73]);
plt.scatter(1,1,90,color='m');
def find(label,num):
    return np.where(label == num)

# with legend


#

idx_1 =np.where(label == 1)
plt.scatter(x[idx_1,1], x[idx_1,0], marker ='x', color = 'm', label='1', s = 37)
idx_2 =np.where(label == 2)

plt.scatter(x[idx_2,1], x[idx_2,0], marker = 's', color ='c', label='2', s = 37)
idx_3 =np.where(label == 3)

plt.scatter(x[idx_3,1], x[idx_3,0], marker = 'o', color = 'r', label='3', s = 37)
plt.legend(loc ='upper right')
plt.show()
print('end');