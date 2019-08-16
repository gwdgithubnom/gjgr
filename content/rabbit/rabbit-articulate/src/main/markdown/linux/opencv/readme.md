export BUILDDIR=$HOME/Programs/gst
export PREFIX=$BUILDDIR/local
mkdir -p $PREFIX


GST_MODULES="gstreamer gst-plugins-base gst-plugins-good gst-plugins-ugly gst-plugins-bad gst-libav gst-rtsp-server"


cmake    -D OPENCV_EXTRA_MODULES_PATH=~/Programs/opencv_contrib/modules     -D PYTHON_EXECUTABLE=/usr/bin/python3   ..

cmake -D OPENCV_EXTRA_MODULES_PATH=/root/Programs/opencv_contrib/modules   -D PYTHON_EXECUTABLE=/usr/bin/python3  ..

/home/gwd/Projects/essen_apps/hapdection4py/darkflow
cmake -D CMAKE_BUILD_TYPE=RELEASE    -D CMAKE_INSTALL_PREFIX=/usr/local    -D INSTALL_PYTHON_EXAMPLES=ON     -D INSTALL_C_EXAMPLES=OFF       -D PYTHON_EXECUTABLE=/home/gwd/Programs/pyenv/shims/python3      -D BUILD_EXAMPLES=ON ..


cmake -D CMAKE_INSTALL_PREFIX=/usr/local  -D CMAKE_SHARED_LINKER_FLAGS=-Wl,-Bsymbolic  -D PYTHON_EXECUTABLE=/usr/bin/python3   -D OPENCV_EXTRA_MODULES_PATH=/root/Programs/opencv_contrib/modules -D WITH_MSMF=ON -D WITH_VFW=ON  -D WITH_FFMPEG=ON  -D BUILD_EXAMPLES=ON  -D BUILD_NEW_PYTHON_SUPPORT=ON  ..

 cmake -D CMAKE_BUILD_TYPE=Release -D PYTHON_EXECUTABLE=/usr/bin/python3   -D OPENCV_EXTRA_MODULES_PATH=/root/Programs/opencv_contrib/modules  -D WITH_FFMPEG=ON  -D BUILD_EXAMPLES=ON  -D BUILD_NEW_PYTHON_SUPPORT=ON  -D WITH_MSMF=ON  -D WITH_V4L=ON -D WITH_XINE=ON -D WITH_XIMEA=ON -D WITH_GSTREAMER=ON -D WITH_GSTREAMER_0_10=ON -D WITH_DSHOW=ON ..


 cmake -D CMAKE_BUILD_TYPE=Release -D PYTHON_EXECUTABLE=/usr/bin/python3   -D OPENCV_EXTRA_MODULES_PATH=/root/Cache/opencv_contrib/modules  -D WITH_FFMPEG=ON  -D BUILD_EXAMPLES=ON  -D BUILD_NEW_PYTHON_SUPPORT=ON  -D WITH_MSMF=ON  -D WITH_V4L=ON -D WITH_XINE=ON -D WITH_XIMEA=ON -D WITH_GSTREAMER=ON -D WITH_GSTREAMER_0_10=ON -D WITH_DSHOW=ON  -D CMAKE_SHARED_LINKER_FLAGS=-Wl,-Bsymbolic  ..


 cmake  -D  CMAKE_BUILD_TYPE=Release -D PYTHON_EXECUTABLE=/usr/bin/python3   -D OPENCV_EXTRA_MODULES_PATH=/home/disk/b/Projects/Build/opencv_contrib/modules  -D WITH_FFMPEG=ON  -D BUILD_EXAMPLES=ON  -D BUILD_NEW_PYTHON_SUPPORT=ON  -D WITH_MSMF=ON  -D WITH_V4L=ON -D WITH_XINE=ON -D WITH_XIMEA=ON -D WITH_GSTREAMER=ON -D WITH_GSTREAMER_0_10=ON -D WITH_DSHOW=ON  -D CMAKE_SHARED_LINKER_FLAGS=-Wl,-Bsymbolic  ..

 cmake  -D  CMAKE_BUILD_TYPE=Release -D BUILD_opencv_python3=ON  -D BUILD_NEW_PYTHON_SUPPORT=ON  -D PYTHON_EXECUTABLE=/home/gwd/Programs/pyenv/shims/python3  -D OPENCV_EXTRA_MODULES_PATH=/home/disk/b/Projects/Build/opencv_contrib/modules  -D WITH_FFMPEG=ON  -D BUILD_EXAMPLES=ON  -D BUILD_NEW_PYTHON_SUPPORT=ON  -D WITH_MSMF=ON  -D INSTALL_C_EXAMPLES=ON -D WITH_V4L=ON -D WITH_XINE=ON -D WITH_XIMEA=ON -D WITH_GSTREAMER=ON -D INSTALL_PYTHON_EXAMPLES=ON -D BUILD_EXAMPLES=ON -D WITH_GSTREAMER_0_10=ON -D WITH_DSHOW=ON  -D CMAKE_SHARED_LINKER_FLAGS=-Wl,-Bsymbolic  ..

https://docs.opencv.org/trunk/d7/d9f/tutorial_linux_install.html

 PYTHON3_EXECUTABLE = <path to python>
PYTHON_INCLUDE_DIR = /usr/include/python<version>
PYTHON_INCLUDE_DIR2 = /usr/include/x86_64-linux-gnu/python<version>
PYTHON_LIBRARY = /usr/lib/x86_64-linux-gnu/libpython<version>.so
PYTHON3_NUMPY_INCLUDE_DIRS = /usr/lib/python3/dist-packages/numpy/core/include/

import cv2
print(cv2.getBuildInformation())
pkg-config
run pip uninstall opencv-contrib-python
cd $BUILDDIR
git clone https://github.com/RidgeRun/gstd-1.x.git gstd
cd $BUILDDIR/gstd
git checkout master
PATH=$PREFIX/bin:$PATH PKG_CONFIG_PATH=$PREFIX/lib/pkgconfig ./autogen.sh && \
PATH=$PREFIX/bin:$PATH PKG_CONFIG_PATH=$PREFIX/lib/pkgconfig ./configure --prefix $PREFIX && \
make && \
make install



cd $BUILDDIR
git clone git@github.com:RidgeRun/gst-rtsp-sink.git
cd gst-rtsp-sink/src
git checkout master
PATH=$PREFIX/bin:$PATH PKG_CONFIG_PATH=$PREFIX/lib/pkgconfig ./autogen.sh --prefix $PREFIX && \
PATH=$PREFIX/bin:$PATH PKG_CONFIG_PATH=$PREFIX/lib/pkgconfig GST_LIBS=$PREFIX/lib/gstreamer-1.0 ./configure --prefix $PREFIX && \
make && \
make install


    FFMPEG:                      NO
      codec:                     NO
      format:                    NO
      util:                      NO
      swscale:                   NO
      resample:                  NO
      gentoo-style:              NO


cd $BUILDDIR
git clone https://github.com/RidgeRun/gst-shark.git
cd $BUILDDIR/gst-shark
git checkout feature/add-support-GStreamer-1.8.1 # change to master after release occurs
PATH=$PREFIX/bin:$PATH PKG_CONFIG_PATH=$PREFIX/lib/pkgconfig ./autogen.sh --prefix $PREFIX && \
PATH=$PREFIX/bin:$PATH PKG_CONFIG_PATH=$PREFIX/lib/pkgconfig ./configure --prefix $PREFIX && \
make && \
make install



home/disk/b/Projects/Build/opencv_contrib/modules/surface_matching/src/t_hash_int.cpp: In function ‘cv::ppf_match_3d::hashtable_int* cv::ppf_match_3d::hashtableCreate(size_t, size_t (*)(unsigned int))’:
/home/disk/b/Projects/Build/opencv_contrib/modules/surface_matching/src/t_hash_int.cpp:78:5: warning: this ‘if’ clause does not guard... [-Wmisleading-indentation]
     if (!hashtbl)
     ^~
/home/disk/b/Projects/Build/opencv_contrib/modules/surface_matching/src/t_hash_int.cpp:81:2: note: ...this statement, but the latter is misleadingly indented as if it is guarded by the ‘if’
  hashtbl->nodes=(hashnode_i**)calloc(size, sizeof(struct hashnode_i*));
  ^~~~~~~


In file included from /home/disk/b/Projects/Build/opencv/modules/photo/src/denoising.cpp:46:0:
/home/disk/b/Projects/Build/opencv/modules/photo/src/fast_nlmeans_denoising_opencl.hpp: In instantiation of ‘bool cv::ocl_calcAlmostDist2Weight(cv::UMat&, int, int, const FT*, int, int, int, int&) [with FT = float; ST = short unsigned int; WT = long int]’:
/home/disk/b/Projects/Build/opencv/modules/photo/src/fast_nlmeans_denoising_opencl.hpp:141:93:   required from here
/home/disk/b/Projects/Build/opencv/modules/photo/src/fast_nlmeans_denoising_opencl.hpp:56:40: warning: integer overflow in expression [-Woverflow]
         std::numeric_limits<ST>::max() * std::numeric_limits<ST>::max() * cn;
         ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
[ 22%] Building CXX object modules/reg/CMakeFiles/opencv_reg.dir/src/mappergradeuclid.cpp.o
[ 22%] Building CXX object modules/video/CMakeFiles/opencv_video.dir/src/optflowgf.cpp.o
[ 22%] Building CXX object modules/imgcodecs/CMakeFiles/o

/home/disk/b/Projects/Build/opencv/modules/videoio/src/cap_dc1394_v2.cpp: In member function ‘virtual bool CvCaptureCAM_DC1394_v2_CPP::grabFrame()’:
/home/disk/b/Projects/Build/opencv/modules/videoio/src/cap_dc1394_v2.cpp:607:5: warning: this ‘if’ clause does not guard... [-Wmisleading-indentation]
     if (nch==3)
     ^~
/home/disk/b/Projects/Build/opencv/modules/videoio/src/cap_dc1394_v2.cpp:610:9: note: ...this statement, but the latter is misleadingly indented as if it is guarded by the ‘if’
         if( rectify && cameraId == VIDERE && nimages == 2 )
         ^~
[ 27%] Linking CXX shared library ../../lib/libopencv_shape.so
[ 28%] Building CXX object modules/dnn/CMakeFiles/opencv_dnn.dir/src/


black_thresholding.cpp.o
/home/disk/b/Projects/Build/opencv_contrib/modules/ccalib/samples/omni_stereo_calibration.cpp: In function ‘bool detecChessboardCorners(const std::vector<std::__cxx11::basic_string<char> >&, std::vector<std::__cxx11::basic_string<char> >&, const std::vector<std::__cxx11::basic_string<char> >&, std::vector<std::__cxx11::basic_string<char> >&, std::vector<cv::Mat>&, std::vector<cv::Mat>&, cv::Size, cv::Size&, cv::Size&)’:
/home/disk/b/Projects/Build/opencv_contrib/modules/ccalib/samples/omni_stereo_calibration.cpp:84:5: warning: this ‘if’ clause does not guard... [-Wmisleading-indentation]
     if (!img_l.empty())
     ^~
/home/disk/b/Projects/Build/opencv_contrib/modules/ccalib/samples/omni_stereo_calibration.cpp:86:2: note: ...this statement, but the latter is misleadingly indented as if it is guarded by the ‘if’
  if (!img_r.empty())


/home/disk/b/Projects/Build/opencv_contrib/modules/stereo/test/test_block_matching.cpp: In member function ‘virtual void CV_BlockMatchingTest::run(int)’:
/home/disk/b/Projects/Build/opencv_contrib/modules/stereo/test/test_block_matching.cpp:100:76: warning: self-comparison always evaluates to false [-Wtautological-compare]
     if(image1.rows != image2.rows || image1.cols != image2.cols || gt.cols != gt.cols || gt.rows != gt.rows)
                                                                    ~~~~~~~~^~~~~~~~~~
/home/disk/b/Projects/Build/opencv_contrib/modules/stereo/test/test_block_matching.cpp:100:98: warning: self-comparison always evaluates to false [-Wtautological-compare]
     if(image1.rows != image2.rows || image1.cols != image2.cols || gt.cols != gt.cols || gt.rows != gt.rows)
                                                                                          ~~~~~~~~^~~~~~~~~~
/home/disk/b/Projects/Build/opencv_contrib/modules/stereo/test/test_block_matching.cpp: In member function ‘virtual void CV_SGBlockMatchingTest::run(int)’:
/home/disk/b/Projects/Build/opencv_contrib/modules/stereo/test/test_block_matching.cpp:184:76: warning: self-comparison always evaluates to false [-Wtautological-compare]
     if(image1.rows != image2.rows || image1.cols != image2.cols || gt.cols != gt.cols || gt.rows != gt.rows)
                                                                    ~~~~~~~~^~~~~~~~~~
/home/disk/b/Projects/Build/opencv_contrib/modules/stereo/test/test_block_matching.cpp:184:98: warning: self-comparison always evaluates to false [-Wtautological-compare]
     if(image1.rows != image2.rows || image1.cols != image2.cols || gt.cols != gt.cols || gt.rows != gt.rows)
                                                                                          ~~~~~~~~^~~~~~~~~~
[ 67%] Linking CXX executable ../../bin/opencv_test_rgbd
[ 67%] Building CXX object modules/stereo/CMakeFiles/opencv_test_stereo.dir/test/test_descriptors.cpp.o

  ^~

