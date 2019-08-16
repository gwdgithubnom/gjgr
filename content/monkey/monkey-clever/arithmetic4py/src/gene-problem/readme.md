# Introduce
This is build for pca model.

# Code
> To the Code
```
# -*- coding: utf-8 -*-
from numpy import mean,cov,double,cumsum,dot,linalg,array,rank
from pylab import plot,subplot,axis,stem,show,figure
import numpy
import pandas
from sklearn.preprocessing import scale
from sklearn.decomposition import PCA
from sklearn import cross_validation
from sklearn.linear_model import LinearRegression

def pcaana(A):
    """ performs principal components analysis
        (PCA) on the n-by-p data matrix A
        Rows of A correspond to observations, columns to variables.

    Returns :
     coeff :
       is a p-by-p matrix, each column containing coefficients
       for one principal component.
     score :
       the principal component scores; that is, the representation
       of A in the principal component space. Rows of SCORE
       correspond to observations, columns to components.
     latent :
       a vector containing the eigenvalues
       of the covariance matrix of A.
    """

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


if __name__=="__main__":


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
```
