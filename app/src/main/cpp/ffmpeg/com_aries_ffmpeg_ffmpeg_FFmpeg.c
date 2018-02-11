/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <libavformat/avformat.h>
#include <android/log.h>
/* Header for class com_aries_ffmpeg_ffmpeg_FFmpeg */

#define  LOG_TAG    "FFmpeg"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

JNIEXPORT void JNICALL Java_com_aries_ffmpeg_ffmpeg_FFmpeg_run(JNIEnv *env, jclass obj) {

    LOGD("FFmpeg_run info");
    char info[40000] = {0};
    av_register_all();
    AVCodec *c_temp = av_codec_next(NULL);
    while(c_temp != NULL){
        if(c_temp->decode!=NULL){
        sprintf(info,"%s[Dec]",info);
        }else{
        sprintf(info,"%s[Enc]",info);
        }
        switch(c_temp->type){
            case AVMEDIA_TYPE_VIDEO:
                    sprintf(info,"%s[Video]",info);
            break;
            case AVMEDIA_TYPE_AUDIO:
                    sprintf(info,"%s[Audio]",info);
            break;
            default:
            sprintf(info,"%s[Other]",info);
            break;
        }
        sprintf(info,"%s[%10s]\n",info,c_temp->name);
        c_temp=c_temp->next;
    }
    LOGD("info:\n%s",info);
}