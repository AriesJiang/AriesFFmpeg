#include <jni.h>

#ifndef _Included_jonesx_videoplayer_VideoPlayer
#define _Included_jonesx_videoplayer_VideoPlayer
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_aries_ffmpeg_VideoPlayer
 * Method:    play
 * Signature: (Ljava/lang/Object;)I
 */
JNIEXPORT jint JNICALL Java_com_aries_ffmpeg_VideoPlayer_play
  (JNIEnv *, jclass, jobject);

#ifdef __cplusplus
}
#endif
#endif
