cmake -D CMAKE_BUILD_TYPE=RELEASE \ -D CMAKE_INSTALL_PREFIX=/usr/local \ -D INSTALL_PYTHON_EXAMPLES=ON \ -D INSTALL_C_EXAMPLES=OFF \ -D WITH_FFMPEG=1 \ -D OPENCV_EXTRA_MODULES_PATH=../../opencv_contrib-3.2.0/modules \ -D BUILD_EXAMPLES=ON ..
XmX2c038e5hk

eval "$(pyenv init -)";

if which pyenv > /dev/null; then eval "$(pyenv init -)"; fi
['', '/usr/lib/python2.7', '/usr/lib/python2.7/plat-x86_64-linux-gnu', '/usr/lib/python2.7/lib-tk', '/usr/lib/python2.7/lib-old', '/usr/lib/python2.7/lib-dynload', '/usr/local/lib/python2.7/dist-packages', '/usr/lib/python2.7/dist-packages', '/usr/lib/python2.7/dist-packages/gtk-2.0']
1.如何解决pyenv的设置环境在opencv中无法识别

sys.path.append('home/gwd/Projects/essen_apps/hapdection4py')




 rdesktop -u gwdwindowsnom@hotmail.com 10.99.5.218 -f


cmake    -D OPENCV_EXTRA_MODULES_PATH=~/Programs/opencv_contrib/modules     -D PYTHON_EXECUTABLE=/usr/bin/python3   ..

cmake -D OPENCV_EXTRA_MODULES_PATH=/root/Programs/opencv_contrib/modules   -D PYTHON_EXECUTABLE=/usr/bin/python3  ..

/home/gwd/Projects/essen_apps/hapdection4py/darkflow
cmake -D CMAKE_BUILD_TYPE=RELEASE \
    -D CMAKE_INSTALL_PREFIX=/usr/local \
    -D INSTALL_PYTHON_EXAMPLES=ON \
    -D INSTALL_C_EXAMPLES=OFF \
    -D OPENCV_EXTRA_MODULES_PATH=/home/gwd/Programs/opencv_contrib/modules \
    -D PYTHON_EXECUTABLE=/home/gwd/Programs/pyenv/shims/python3  \
    -D BUILD_EXAMPLES=ON ..

['', '/home/gwd/Programs/pyenv/versions/2.7.13/lib/python27.zip', '/home/gwd/Programs/pyenv/versions/2.7.13/lib/python2.7', '/home/gwd/Programs/pyenv/versions/2.7.13/lib/python2.7/plat-linux2', '/home/gwd/Programs/pyenv/versions/2.7.13/lib/python2.7/lib-tk', '/home/gwd/Programs/pyenv/versions/2.7.13/lib/python2.7/lib-old', '/home/gwd/Programs/pyenv/versions/2.7.13/lib/python2.7/lib-dynload', '/home/gwd/Programs/pyenv/versions/2.7.13/lib/python2.7/site-packages', '/usr/lib64/python27.zip', '/usr/lib64/python2.7', '/usr/lib64/python2.7/plat-linux2', '/usr/lib64/python2.7/lib-dynload', '/usr/lib64/python2.7/site-packages', '/usr/lib/python2.7/site-packages', '/usr/lib64/python2.7/lib-tk', '/usr/lib64/python2.7/lib-old', '/usr/lib64/python2.7/lib-dynload', '/usr/lib64/python2.7/site-packages/gtk-2.0', '/usr/lib/python2.7/dist-packages', '/usr/lib/python2.7/dist-packages/gtk-2.0', '/usr/lib/python2.7', '/usr/lib/python2.7/plat-x86_64-linux-gnu', '/usr/lib/python2.7/lib-tk', '/usr/lib/python2.7/lib-old', '/usr/lib/python2.7/lib-dynload', '/usr/local/lib/python2.7/dist-packages']



['', '/usr/lib/python2.7', '/usr/lib/python2.7/plat-x86_64-linux-gnu', '/usr/lib/python2.7/lib-tk', '/usr/lib/python2.7/lib-old', '/usr/lib/python2.7/lib-dynload', '/usr/local/lib/python2.7/dist-packages', '/usr/lib/python2.7/dist-packages', '/usr/lib/python2.7/dist-packages/gtk-2.0']

