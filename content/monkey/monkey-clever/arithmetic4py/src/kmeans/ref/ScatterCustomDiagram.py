__author__ = 'gwd'
import types
import numpy as np
from numpy import arange, pi, cos, sin
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt
class ScatterCutomDiagram:
    def __init__(self):
        self.fig = plt.figure();
        self.ax=self.fig.add_subplot(111, projection='3d');
        rx, ry = 3., 1.
        area = rx * ry * pi
        theta = arange(0, 2*pi + 0.01, 0.1)
        self.verts = list(zip(rx/area*cos(theta), ry/area*sin(theta)))
    def getDiagramDer(self,x,y,s,c):#to show the diagram
       self.ax.scatter(x, y, s, c, marker=None, verts=self.verts)

    def show(self):
        ax=self.ax;
        ax.set_xlabel('X Label:Hua Ban')
        ax.set_ylabel('Y Label:Hua W')
        ax.set_zlabel('Z Label:Hua Z')
        plt.show()



