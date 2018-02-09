#include "jni_helper.hpp"

#include <android/log.h>
#define TAG "TanTu"

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG    , TAG, __VA_ARGS__)

static JavaVM * g_jvm = 0;
extern JavaVM * GetJVM()
{
  return g_jvm;
}

extern "C"
{
  JNIEXPORT jint JNICALL
  JNI_OnLoad(JavaVM * jvm, void *)
  {
    g_jvm = jvm;

    return JNI_VERSION_1_6;
  }

JNIEXPORT void JNICALL
JNI_OnUnload(JavaVM *, void *)
{
  g_jvm = 0;
}
} // extern "C"

namespace jni
{
JNIEnv * GetEnv()
{
  JNIEnv * env;
  return env;
}

JavaVM * GetJVM()
{
  return g_jvm;
}

jmethodID GetMethodID(JNIEnv * env, jobject obj, char const * name, char const * signature)
{
  TScopedLocalClassRef clazz(env, env->GetObjectClass(obj));

  jmethodID mid = env->GetMethodID(clazz.get(), name, signature);
  return mid;
}

jmethodID GetStaticMethodID(JNIEnv * env, jclass clazz, char const * name, char const * signature)
{
  jmethodID mid = env->GetStaticMethodID(clazz, name, signature);
  return mid;
}

jmethodID GetConstructorID(JNIEnv * env, jclass clazz, char const * signature)
{
  jmethodID const ctorID = env->GetMethodID(clazz, "<init>", signature);
  return ctorID;
}


  jclass GetGlobalClassRef(JNIEnv * env, char const * sig)
  {
    jclass klass = env->FindClass(sig);
    return static_cast<jclass>(env->NewGlobalRef(klass));
  }

    std::string ToNativeString(JNIEnv * env, jstring str)
  {
      std::string result;
    char const * utfBuffer = env->GetStringUTFChars(str, 0);
    if (utfBuffer)
    {
      result = utfBuffer;
      env->ReleaseStringUTFChars(str, utfBuffer);
    }
    return result;
  }

  jstring ToJavaString(JNIEnv * env, char const * s)
  {
    return env->NewStringUTF(s);
  }

jclass GetStringClass(JNIEnv * env)
{
  return env->FindClass(GetStringClassName());
}

  char const * GetStringClassName()
  {
    return "java/lang/String";
  }

  // TODO
  // make ScopedLocalRef wrapper similar to https://android.googlesource.com/platform/libnativehelper/+/jb-mr1.1-dev-plus-aosp/include/nativehelper/ScopedLocalRef.h
  // for localrefs automatically removed after going out of scope

  // This util method dumps content of local and global reference jni tables to logcat for debug and testing purposes
  void DumpDalvikReferenceTables()
  {
    JNIEnv * env = GetEnv();
    jclass vm_class = env->FindClass("dalvik/system/VMDebug");
    jmethodID dump_mid = env->GetStaticMethodID(vm_class, "dumpReferenceTables", "()V");
    env->CallStaticVoidMethod(vm_class, dump_mid);
    env->DeleteLocalRef(vm_class);
  }
} // namespace jni
