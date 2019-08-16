from numpy import mean,cov,double,cumsum,dot,linalg,array,rank
from pylab import plot,subplot,axis,stem,show,figure
import numpy
from matplotlib import cm
from mpl_toolkits.mplot3d.axes3d import Axes3D
import matplotlib.pyplot as plt
import math
import pandas
import matplotlib.pyplot as plt
from sklearn.preprocessing import scale
from sklearn.decomposition import PCA
from sklearn import cross_validation
from sklearn.linear_model import LinearRegression
from sklearn import svm
def pcaana(A):

    # computing eigenvalues and eigenvectors of covariance matrix
    M = (A-mean(A.T,axis=1)).T # subtract the mean (along columns)
    [latent,coeff] = linalg.eig(cov(M)) # attention:not always sorted
    score = dot(coeff.T,M) # projection of the data in the new space
    coeff, score, latent = pcaana(A)
    print(coeff,score,latent)
    figure("init figure")
    #subplot(121)

    # every eigenvector describe the direction
    # of a principal component.
    m = mean(A,axis=1)
    plot([0, -coeff[0,0]*2]+m[0], [0, -coeff[0,1]*2]+m[1],'--k')
    plot([0, coeff[1,0]*2]+m[0], [0, coeff[1,1]*2]+m[1],'--k')
    plot(A[0,:],A[1,:],'ob') # the data
    axis('equal')
    subplot(122)
    # new data
    plot(score[0,:],score[1,:],'*g')
    axis('equal')
    show()
    return coeff,score,latent

def sigmod(x):
    return 1.0/(1+math.exp(-x))



"""
"rs2273298","rs4646092","rs1339367","rs2038095"
"""
if __name__=="__main__":

    data=pandas.read_table("data-encode.txt",sep=' ')

    #data=data[["V1","rs2273298","rs4646092","rs1339367","rs2038095"]]
    print("success read test.csv file")
    #data=data.reset_index().values
    #data=data.as_matrix()

    #print(data)
    #exit()
    y=data.iloc[0:,0]
    y=y.as_matrix()
    Y=y
    x=data.iloc[0:,1:]
    x=x.as_matrix()
    X=x
    pca=PCA(n_components=3, copy=False)
    temp=pca.fit(x)
    temp=pca.transform(x)
    #print(temp,type(temp))
    x=temp
    temp=pandas.DataFrame(temp)
    #numpy.savetxt("multi_phenos_pca_10.txt",temp)

    #print(temp.describe())

    clf=svm.SVC(kernel='linear')
    clf.fit(x,y)
    print(x,y)
    a=numpy.random.random(50)
    b=numpy.random.random(50)
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.scatter(x[:,0],x[:,1],x[:,2],s=100,c=y,marker='o')
    #plt.scatter(x[:, 0], x[:, 1],s=202,c=y,alpha=1.5)
    plt.axis('tight')
    plt.show()

    exit()
    print("finish the matrix compute")


    #this is save a txt file
    #numpy.savetxt("multi_phenos_pca.txt",temp)
    d=pandas.DataFrame(temp)
    print(d)