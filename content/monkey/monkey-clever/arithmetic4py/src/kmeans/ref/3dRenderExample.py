__author__ = 'gwd'
from mpl_toolkits.mplot3d import axes3d
import matplotlib.pyplot as plt,numpy as np
plt.clf()
fig = plt.figure(1)
ax = fig.gca(projection='3d')
X, Y, Z = axes3d.get_test_data(0.05)

ax.plot_surface(X, Y, Z, rstride=8, cstride=8, alpha=0.3)
cset = ax.contourf(X, Y, Z, zdir='x', offset=-40, cmap=plt.cm.jet)
cset = ax.contourf(X, Y, Z, zdir='y', offset=40, cmap=plt.cm.jet)

### strating here:

# normalize Z to [0..1]
Z=Z-Z.min()
Z=Z/Z.max()

#use zoom to make your data smoother
from scipy.ndimage.interpolation import zoom

#make data 5 times smoother
X=zoom(X,5)
Y=zoom(Y,5)
Z=zoom(Z,5)

#draw a surface at -100, using the facecolors command to color it with the values of Z
cset = ax.plot_surface(X, Y, np.zeros_like(Z)-100,facecolors=plt.cm.jet(Z),shade=False)


ax.set_xlabel('X')
ax.set_xlim(-40, 40)
ax.set_ylabel('Y')
ax.set_ylim(-40, 40)
ax.set_zlabel('Z')
ax.set_zlim(-100, 100)
plt.show()
cb = plt.cm.ScalarMappable(cmap=plt.cm.jet)
cb.set_array(Z)
plt.colorbar(cb)
plt.show()