# AriesFFmpeg

###FFmpeg部分
   FFmpeg基于3.4.1版本，通过Ubuntu16.04.2 + ndk14编译，环境配置（gcc， sudo apt-get install build-essential， git）
注意地方：build脚本配置，注意android版本（目前使用19，我是用9编译会失败）

###CMakeLists语法入门
1. [自动包含及第三方so库调用](http://blog.csdn.net/qq_35064774/article/details/52955242)
2. [Android NDK开发扫盲及最新CMake的编译使用](https://juejin.im/post/595da4e25188250d8b65ddbf)
3. [引入C++ support](http://blog.csdn.net/martin20150405/article/details/53284442)
4. [官方文档](https://developer.android.com/studio/projects/add-native-code.html?hl=zh-cn)

###JNI部分
写FFmpeg.c，生成头文件FFmpeg.h命令：
```
E:xxx\AriesFFmpeg\app\src\main\java>javah -classpath .  com.aries.ffmpeg.ffmpeg.FFmpeg
```
ps: 需要配置好Java环境即使java -verision，显示正常也不一定是有完整的Java环境！！