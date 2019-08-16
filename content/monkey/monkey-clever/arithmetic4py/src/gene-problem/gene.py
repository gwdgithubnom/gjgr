"""
build for data pre-procesing
"""
import pandas

def pre():
    #genotype=pandas.read_table('genotype.dat',sep=' ')
    genotype=pandas.read_table('data-encode.txt',sep=' ')
    #AA=0000
    #AC=0001
    #AG=0010
    #AT=0011
    """
      genotype[,i][genotype[,i]=="TT"] <- 33
      genotype[,i][genotype[,i]=="TC"] <- 31
      genotype[,i][genotype[,i]=="CC"] <- 11
      genotype[,i][genotype[,i]=="CT"] <- 13
      genotype[,i][genotype[,i]=="AA"] <- 00
      genotype[,i][genotype[,i]=="GG"] <- 22
      genotype[,i][genotype[,i]=="GA"] <- 20
      genotype[,i][genotype[,i]=="AG"] <- 02
      genotype[,i][genotype[,i]=="GT"] <- 23
      genotype[,i][genotype[,i]=="AC"] <- 01
      genotype[,i][genotype[,i]=="TG"] <- 32
      genotype[,i][genotype[,i]=="CA"] <- 10
      genotype[,i][genotype[,i]=="GC"] <- 21
      genotype[,i][genotype[,i]=="CG"] <- 12
      genotype[,i][genotype[,i]=="AT"] <- 03
      genotype[,i][genotype[,i]=="ID"] <- 31
      genotype[,i][genotype[,i]=="II"] <- 33
      genotype[,i][genotype[,i]=="DD"] <- 11
      genotype[,i][genotype[,i]=="TA"] <- 30
     """

    # this is to relpace the value
    #genotype.replace(['TT','TC'],[33,31],inplace=True)   ,
    #l=['TT','TC','CC','CT','AA','GG','GA','AG','GT','AC','TG','CA','GC','CG','AT','ID','II','DD','TA']
    #genotype.replace(['TT','TC','CC','CT','AA','GG','GA','AG','GT','AC','TG','CA','GC','CG','AT','ID','II','DD','TA'],[33,31,11,13,0,22,20,2,23,1,32,10,21,12,3,31,33,11,30],inplace=True)
    #print(genotype.xs(1)[1])
    #print(genotype.iat(3,1))
    #genotype=genotype.iloc[0:,0:10]
    print(type(genotype))
    genotype.to_csv("data-encode.csv",index=False)


def analysis():
    genotype=pandas.read_excel('test.xlsx','data')
    print(genotype.describe())
    # Author: Andreas Mueller <amueller@ais.uni-bonn.de>
    #
    # License: BSD 3 clause
    from sklearn.pipeline import Pipeline, FeatureUnion
    from sklearn.grid_search import GridSearchCV
    from sklearn.svm import SVC
    from sklearn.datasets import load_iris
    from sklearn.decomposition import PCA
    from sklearn.feature_selection import SelectKBest
    iris = load_iris()

    X, y = iris.data, iris.target

    # This dataset is way to high-dimensional. Better do PCA:
    pca = PCA(n_components=2)

    # Maybe some original features where good, too?
    selection = SelectKBest(k=1)

    # Build estimator from PCA and Univariate selection:

    combined_features = FeatureUnion([("pca", pca), ("univ_select", selection)])

    # Use combined features to transform dataset:
    X_features = combined_features.fit(X, y).transform(X)

    svm = SVC(kernel="linear")

    # Do grid search over k, n_components and C:

    pipeline = Pipeline([("features", combined_features), ("svm", svm)])

    param_grid = dict(features__pca__n_components=[1, 2, 3],
                      features__univ_select__k=[1, 2],
                      svm__C=[0.1, 1, 10])

    grid_search = GridSearchCV(pipeline, param_grid=param_grid, verbose=10)
    grid_search.fit(X, y)
    print(grid_search.best_estimator_)




if __name__=="__main__":
    print(__doc__)
    #analysis()
    pre()