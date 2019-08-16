import os
import random

#读取数据的函数
def readData(fileName, classIndex):
    dataFile = open(fileName)               #读取文件
    dataList = {}
    i = 0
    for line in dataFile:
        if len(line.strip('\n'))!= 0:
            aList = []
            for aData in line.split(','):
                aList.append(aData.strip('\n'))           #将一行数据加入一个list
            if classIndex > 0:
                aList.pop(classIndex)                                  #将类标签移除
            dataList[i] = aList              #将数据加入List
            i = i+1
    #数据读取完毕
    dataFile.close()
    print("数据读取完毕，长度是"+str(len(dataList))+"数据是：")
    print(dataList)
    return dataList

def distance(dataList1, dataList2):
    x = 0.0
    for i in range(0,len(dataList1)):
        x = x + (float(dataList1[i]) - float(dataList2[i]))**2
    return x

#迭代函数，传入数据列表和，中心点,以数据点为中心，将数据列表分类，返回分类结果
def iteratorFunction(dataList,basePoints):

    #初始化返回的结果
    classMap = {}
    baseDistance = {}
    print("获得到的basePoint为："+str(basePoints))
    for classNum in basePoints:
        aClass = []
        classMap[classNum] = aClass
        baseDistance[classNum] = dataList[classNum].copy()
    print("初始化的baseDistance为："+str(baseDistance))

    for aKey in dataList.keys():
        aData = dataList.get(aKey)
        #计算这条数据到分类点的距离
        dis = 1000000000000000
        newNum = 0
        for classNum in basePoints:
            aDis = distance(baseDistance[classNum],aData)
            if dis > aDis:
                dis = aDis
                newNum = classNum
        classMap[newNum].append(aKey)
    return classMap

#寻找新的聚类中心的函数

def findNewCenters(classMap,dataList):
    basePoint = []
    for akey in classMap.keys():

        indexList = classMap.get(akey)              #获得数据，开始找它的中心点
        center = []
        for i in range(0,len(dataList.get(indexList[0]))):         #这个是维数
            #print("维度"+str(i))
            partCenter = 0
            for index in indexList:
                #print(index)
                dataArray = dataList[index]
                partCenter = partCenter + float(dataArray[i])
                #print("partCenter"+str(partCenter))
            partCenter = partCenter / float(len(indexList))

            center.append(partCenter)

        #获得了理论上的中心点，找实际的点：
        #print("理论上的中心点"+str(center))
        trueCenter = -1
        aTemp = 100000000000000000
        for index in indexList:
            aDistance = distance(center, dataList[index])
            if aDistance < aTemp:
                aTemp = aDistance
                trueCenter = index

        basePoint.append(trueCenter)
    return basePoint


#Kmeans 算法

def kMeans(dataList, numberOfClass):
    #============================================================================初始化
    #创建用来存储分类结果的容器
    #print(len(dataList.keys()))
    basePoint = []                              #选出来的中心点
    for i in range(0, numberOfClass):
        x = random.randint(0,len(dataList.keys()))
        basePoint.append(x)
    print('初始化容器成功，所选的随机点是'+str(basePoint))

    #============================================================================迭代
    classMap = {}
    classMap = iteratorFunction(dataList, basePoint)
    print("一次迭代之后：")
    print(classMap)

    #============================================================================重新找中心
    newBasePoint = findNewCenters(classMap, dataList)
    print("找到的新的中心是："+str(newBasePoint))

    while distance(basePoint, newBasePoint) > 1.0:
        basePoint = newBasePoint.copy()
        classMap = iteratorFunction(dataList, basePoint)
        print("一次迭代之后：")
        print(classMap)
        #============================================================================重新找中心
        newBasePoint = findNewCenters(classMap, dataList)
        print("找到的新的中心是："+str(newBasePoint))
    return classMap
    #============================================================================输出结果

if __name__ == '__main__':
    fileName = "C:\\Users\\gwd\\PycharmProjects\\kmeans\\iris.data"
    dataList = readData(fileName,4)
    kMeans(dataList, 3)