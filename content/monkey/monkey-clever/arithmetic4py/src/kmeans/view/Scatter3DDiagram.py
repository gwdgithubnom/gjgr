__author__ = 'gwd'
import types
import numpy as np
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt
class Scatter3DDiagram:
    def __init__(self):
        self.fig = plt.figure();
        self.ax=self.fig.add_subplot(111, projection='3d');

    def addDiagramData_RO(self,xs,ys,zs):
        self.ax.scatter(xs, ys, zs, c='r', marker='o');

    def addDiagramData_B567(self,xs,ys,zs):
        self.ax.scatter(xs, ys, zs, c='b', marker='^');

    def addDiagramData_CV(self,xs,ys,zs):
        self.ax.scatter(xs, ys, zs, c='c', marker='v');

    def getDiagramDer(self,xs,ys,zs):#to show the diagram
        for c, m in [('r', 'o'), ('b', '^'),('c', 'v')]:
            self.ax.scatter(xs, ys, zs, c=c, marker=m);

    def show(self):
        ax=self.ax;
        ax.set_xlabel('X Label:Hua Ban')
        ax.set_ylabel('Y Label:Hua W')
        ax.set_zlabel('Z Label:Hua Z')
        plt.show()



