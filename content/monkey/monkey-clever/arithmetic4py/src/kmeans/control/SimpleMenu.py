#coding=utf-8
from tkinter import *
from view.Scatter3DDiagram import Scatter3DDiagram
from view.Scatter2DDiagram import Scatter2DDiagram
from tkinter import messagebox
import numpy as np
from control.Kmeans import *
class SimpleMenu:
    def __init__(self,fileName):
        root = Tk()
        self.initData= np.random.rand(100,2);
        self.fileName=fileName;
        self.result={}
        root.title("Menu world")
        root.geometry('100x150')
      #  fileName = "C:\\Users\\gwd\\PycharmProjects\\kmeans\\iris.data";
        Button(root, text="初始化生成数据测试", command =self.button4).pack();
        Button(root, text="运行生成数据的K-Means求解", command =self.button5).pack();
        Button(root, text="加载数据irris数据", command =self.button1).pack();
        Button(root, text="运行K-Means", command = self.button2).pack();
        Button(root, text="再运行K-Means", command = self.button3).pack();
        root.mainloop()
    def button1(self):
        self.dataList = readData(self.fileName,4);
        data=self.dataList;
        k=self.dataList.keys();
        diagram=Scatter3DDiagram();
        xs=[];
        ys=[];
        zs=[];
        for i in k:
            xs.append(float(data.get(i)[0]));

            ys.append(float(data.get(i)[1]));
            zs.append(float(data.get(i)[2]));
        diagram.addDiagramData_B567(xs,ys,zs);
        diagram.show();


    def button2(self):
        if(len(self.result) >4):
            messagebox.showinfo(title='K-means Finished', message = "K-means运行结束");
        self.dataList = readData(self.fileName,4);
        self.result=kMeans(self.dataList, 3)
        result=self.result;
        print("result:",self.result)
        k=self.result.keys();
        data=self.dataList;
        k=self.dataList.keys();
        diagram=Scatter3DDiagram();
        xs=[];
        ys=[];
        zs=[];
        for i in result.popitem()[1]:
            print('abc',type(i))
            xs.append(float(data.get(i)[0]));
            ys.append(float(data.get(i)[1]));

            zs.append(float(data.get(i)[2]));
        diagram.addDiagramData_B567(xs,ys,zs);
        xs=[];
        ys=[];
        zs=[];
        for i in result.popitem()[1]:
            xs.append(float(data.get(i)[0]));
            ys.append(float(data.get(i)[1]));
            zs.append(float(data.get(i)[2]));
        diagram.addDiagramData_CV(xs,ys,zs);
        xs=[];
        ys=[];
        zs=[];
        for i in result.popitem()[1]:
            xs.append(float(data.get(i)[0]));
            ys.append(float(data.get(i)[1]));
            zs.append(float(data.get(i)[2]));
        diagram.addDiagramData_RO(xs,ys,zs);
        diagram.show();

    def button3(self):
        print("to do3");

    def button4(self):
        self.initData= np.random.rand(100,2);
        x=self.initData;
        diagram=Scatter2DDiagram(x[:,0],x[:,1]);
        diagram.clear();
        diagram.addDiagram(x[:,0],x[:,1]);
        diagram.show();

    def button5(self):
        print("to do4");