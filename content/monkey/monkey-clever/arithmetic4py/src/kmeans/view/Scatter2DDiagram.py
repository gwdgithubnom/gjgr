from numpy import *
import matplotlib
import numpy as np
import matplotlib.pyplot as plt
class Scatter2DDiagram:
        def __init__(self,x,y):
            self.x=x;
            self.y=y;

            self.figure = plt.figure('default');
            plt.legend(loc ='upper right')
            plt.scatter(x,y);
            #plt.subplot(211);
        def show(self):
            plt.show();
        def clear(self):
            self.figure.clear();
        def addDiagram(self,x,y):
            plt.scatter(x,y);
        def setDiagram(self,title):
            plt.close('all');
            self.figure = plt.figure(title);
        def changeSubPlot(self,n):
            plt.subplot(n)
        def addDiagramElement_XM(self,x,y,label):
            plt.scatter(x, y, marker ='x', color = 'r', label=label, s = 37)
        def addDiagramElement_OG(self,x,y,label):
            plt.scatter(x, y, marker ='O', color = 'g', label=label, s = 37)
        def addDiagramElement_SD(self,x,y,label):
            plt.scatter(x, y, marker ='s', color = 'd', label=label, s = 37)