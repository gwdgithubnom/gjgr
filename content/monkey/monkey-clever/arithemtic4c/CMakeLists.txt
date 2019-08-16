cmake_minimum_required(VERSION 3.4)
project(arithmetic4c)

# To set environment
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
set(SOURCE_DIR ${CMAKE_CURRENT_SOURCE_DIR}/src)
# Set new modules: ai exam.
set(MODULES ai exam)
set(SOURCE_FILES ${SOURCE_DIR}/test.cpp)
# add_executable(arithmetic4c ${SOURCE_FILES})
# ${CMAKE_CURRENT_SOURCE_DIR}
# To set new target for runing the project program.
add_executable(mytarget ${SOURCE_FILES})
add_executable(run ${PROJECT_SOURCE_DIR}/run.cpp)

# set(SOURCE_FILES main.cpp com/microsoft/arithmetic/contest/work_pragraph.c src/test.cpp src/math_arithmetic.cpp)
# add_executable(arithmetic4c ${SOURCE_FILES})