import numpy as np
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt


def randrange(n, vmin, vmax):
    return (vmax - vmin)*np.random.rand(n) + vmin

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
n = 100
for c, m, zl, zh in [('r', 'o', -50, -25), ('b', '^', -30, -5)]:
    xs = randrange(n, 23, 32)
    print("xs:",xs);
    ys = randrange(n, 0, 100)
    print("ys:",ys);
    zs = randrange(n, zl, zh)
    print("zs",zs)
    ax.scatter(xs, ys, zs, c=c, marker=m)
#26.82102238   2.24563009  -41.22760123
ax.set_xlabel('X Label')
ax.set_ylabel('Y Label')
ax.set_zlabel('Z Label')

plt.show()

