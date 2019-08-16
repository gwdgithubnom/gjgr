# Install Caffe and Opencv

## 1、安装基本环境：gcc,gcc+,make，fortrain等等
sudo yum install gcc cmake vim gcc-gfortran gcc-c++ git python-devl numpy  python-nose python-setuptools 

## 2、安装显卡驱动
## 3、安装cuda

## 4、install lib for opencv
sudo yum install protobuf-devel leveldb-devel openblas-devel snappy-devel  boost-devel hdf5-devel gflags-devel glog-devel lmdb-devel  blas-devel lapack-devel atlas-devel

## 5、 install opencv
sudo yum install opencv-devel gflags-devel glog-devel lmdb-devel gtk2-devel libdc1394-devel libv4l-devel ffmpeg-devel gstreamer-plugins-base-devel libpng-devel libjpeg-turbo-devel jasper-devel openexr-devel libtiff-devel libwebp-devel
download opencv and make, compile.....

## 6、install opencv 
$ yum install git

$ mkdir opencv-build

$ cd opencv-build

$ git clone https://github.com/Itseez/opencv.git

$ cd opencv

$ git checkout tags/2.4.8.2

$ mkdir build

$ cd build

$ cmake -D CMAKE_BUILD_TYPE=RELEASE -D CMAKE_INSTALL_PREFIX=/usr/local ..

$ make

$ sudo make install

$ cp /usr/local/lib/python2.7/site-packages/cv2.so /usr/lib/python2.7/site-packages

## 7、install caffe

$ git clone https://github.com/BVLC/caffe

$ for req in $(cat caffe/python/requirements.txt); do sudo pip install $req; done

$ cd caffe

$ cp Makefile.config.example Makefile.config

$ vim Makefile.config

>\###
\#Edit the line
BLAS := atlas
\#Change ‘atlas’ to ‘open’
\#BLAS := atlas--->BLAS := open
\#And add a new line under it:
BLAS_INCLUDE := /usr/include/openblas
\#Then edit line: (located under “PYTHON_INCLUDE := /usr/include/python2.7 \”)

 /usr/lib/python2.7/dist-packages/numpy/core/include
Change python directory to ‘/usr/lib64/python2.7/site-packages/’, the line should then looks like that
        /usr/lib64/python2.7/site-packages/numpy/core/include

\#setting gpu state
CPU_ONLY := 1
#CPU_ONLY := 1

>\###

$ sudo make all

$ sudo make runtest

$ sudo make pycaffe

$ sudo make distribute
