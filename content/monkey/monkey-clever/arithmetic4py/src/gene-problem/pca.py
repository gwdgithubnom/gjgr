from numpy import mean,cov,double,cumsum,dot,linalg,array,rank
from pylab import plot,subplot,axis,stem,show,figure
import numpy
import pandas
import math
import matplotlib.pyplot as plt
import numpy as np
from sklearn.preprocessing import scale
from sklearn.decomposition import PCA
from sklearn import cross_validation
from sklearn.linear_model import LinearRegression
from mpl_toolkits.mplot3d import Axes3D
from  sklearn import decomposition
from  sklearn import datasets



# to pca 3 to visual
def pca3perform():
    data=pandas.read_table("data-encode.txt",sep=' ')

    print("success read test.csv file")
    #data=data.reset_index().values
    #data=data.as_matrix()
    y=data.iloc[0:,0]
    y=y.as_matrix()

    x=data.iloc[0:,1:]
    x=x.as_matrix()

    pca=PCA(n_components=3, copy=False)
    temp=pca.fit(x)
    temp=pca.transform(x)
    print(temp,type(temp))
    x=temp
    temp=pandas.DataFrame(temp)
    perform(pca,x,y)

def perform(pca,X,y):
    fig = plt.figure(1, figsize=(50, 50))
    plt.clf()
    ax = Axes3D(fig, rect=[0, 0, .95, 1], elev=48, azim=134)
    plt.cla()
    for name, label in [('Setosa', 0), ('Versicolour', 1), ('Virginica', 2)]:
            ax.text3D(X[y == label, 0].mean(),
                      X[y == label, 1].mean() + 1.5,
                      X[y == label, 2].mean(), name,
                      horizontalalignment='center',
                      bbox=dict(alpha=.5, edgecolor='w', facecolor='w'))
    # Reorder the labels to have colors matching the cluster results
    y = np.choose(y, [1, 2, 0]).astype(np.float)
    ax.scatter(X[:, 0], X[:, 1], X[:, 2], c=y, cmap=plt.cm.spectral)

    x_surf = [X[:, 0].min(), X[:, 0].max(),
              X[:, 0].min(), X[:, 0].max()]
    y_surf = [X[:, 0].max(), X[:, 0].max(),
              X[:, 0].min(), X[:, 0].min()]
    x_surf = np.array(x_surf)
    y_surf = np.array(y_surf)
    v0 = pca.transform(pca.components_[[0]])
    v0 /= v0[-1]
    v1 = pca.transform(pca.components_[[1]])
    v1 /= v1[-1]

    ax.w_xaxis.set_ticklabels([])
    ax.w_yaxis.set_ticklabels([])
    ax.w_zaxis.set_ticklabels([])

    plt.show()

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


def en(X=[[0,0]]):
    X=X-numpy.mean(X,axis=0)
    [u,s,v]=numpy.linalg.svd(X)
    v=v.transpose()
    #v=v[:,:numcomp]
    return numpy.dot(X,v)

def sigmod(x):
    return int(round(1.0/(1+math.exp(-x)),0))

if __name__=="__main__":

    pca3perform()
    exit()


    A = array([ [2.4,0.7,2.9,2.2,3.0,2.7,1.6,1.1,1.6,0.9],[2.4,1.7,2.9,2.2,3.0,2.7,2.6,1.1,1.6,0.9],
               [2.5,0.5,2.2,1.9,3.1,2.3,2,1,1.5,1.1] ])
    data=pandas.read_csv("multi_phenos.txt",sep=' ',header=None)
    dtype = [('Col1','int32'), ('Col2','float32'), ('Col3','float32')]
    values = numpy.zeros(20, dtype=dtype)
    print(values,type(values))
    index = ['Row'+str(i) for i in range(1, len(values)+1)]
    df = pandas.DataFrame(values, index=index)
    print(df,type(values))
    print(data,type(data))
    #data=data.reset_index().values
    data=data.as_matrix()
    print(data,type(data))
    pca=PCA(n_components=1, copy=False, whiten=False)
    temp=pca.fit(data)
    temp=pca.transform(data)
    print(temp,type(temp))
    #temp=pandas.DataFrame(temp)
    """
    for index, row in temp.iterrows():
        for col_name in temp.columns:
           print("#"+row[col_name])
    """
    for i in range(0, len(temp)):
         temp[i]=sigmod(temp[i])

    #this is save a txt file
    numpy.savetxt("multi_phenos_pca.txt",temp)
    d=pandas.DataFrame(temp)
    print(d)

