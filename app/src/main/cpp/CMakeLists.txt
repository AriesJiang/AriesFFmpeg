cmake_minimum_required(VERSION 3.4.1)
#把FFmpeg的头文件都包涵进来
set(lib_src_DIR ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})
include_directories(
        ${CMAKE_SOURCE_DIR}/include
)

add_library(avcodec-57_lib SHARED IMPORTED)
set_target_properties(avcodec-57_lib PROPERTIES IMPORTED_LOCATION
                             ${lib_src_DIR}/libavcodec-57.so)


add_library(avformat-57_lib SHARED IMPORTED)
set_target_properties(avformat-57_lib PROPERTIES IMPORTED_LOCATION
                        ${lib_src_DIR}/libavformat-57.so)

add_library(avutil-55_lib SHARED IMPORTED)
set_target_properties(avutil-55_lib PROPERTIES IMPORTED_LOCATION
                        ${lib_src_DIR}/libavutil-55.so)

add_library(swresample-2_lib SHARED IMPORTED)
set_target_properties(swresample-2_lib PROPERTIES IMPORTED_LOCATION
                        ${lib_src_DIR}/libswresample-2.so)

add_library(swscale-4_lib SHARED IMPORTED)
set_target_properties(swscale-4_lib PROPERTIES IMPORTED_LOCATION
                        ${lib_src_DIR}/libswscale-4.so)

#添加c++11 标准库
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11 -fexceptions -frtti")

# build application's shared lib
#add_library(VideoPlayer SHARED
#            VideoPlayer.cpp)

# 取代上面两句
# 查找cpp目录下的所有源文件 并将名称保存到 SRC_LIST 变量
aux_source_directory(${CMAKE_SOURCE_DIR} SRC_LIST)

# 查找cpp/core目录下的所有源文件 并将名称保存到 CORE_SRC_LIST 变量
aux_source_directory(${CMAKE_SOURCE_DIR}/core CORE_SRC_LIST)
# 并拼接到路径列表 SRC_LIST
list(APPEND SRC_LIST ${CORE_SRC_LIST})

# 生成链接库
add_library (VideoPlayer SHARED ${SRC_LIST})

# 导入cpp目录下的所有头文件
include_directories(${CMAKE_SOURCE_DIR})

# Include libraries needed for VideoPlayer lib
target_link_libraries(VideoPlayer
log
android
avcodec-57_lib
avformat-57_lib
avutil-55_lib
swresample-2_lib
swscale-4_lib)

#CMakeLists 入门
#http://blog.csdn.net/qq_35064774/article/details/52955242
#https://juejin.im/post/595da4e25188250d8b65ddbf
#http://blog.csdn.net/martin20150405/article/details/53284442
#https://developer.android.com/studio/projects/add-native-code.html?hl=zh-cn