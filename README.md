# AriesFFmpeg

FFmpeg基于3.4.1版本，通过Ubuntu16.04.2 + ndk14编译，环境配置（gcc， sudo apt-get install build-essential， git）
注意地方：build脚本配置，注意android版本（目前使用19，我是用9编译会失败）

编写FFmpeg.c，生成头文件命令：
E:xxx\AriesFFmpeg\app\src\main\java>javah -classpath .  com.aries.ffmpeg.ffmpeg.FFmpeg
ps: 需要配置好Java环境即使java -verision，显示正常也不一定是有完整的Java环境！！