#pragma once

#include <jni.h>
#include <cwchar>

#include <string>
#include "ScopedLocalRef.hpp"

namespace jni
{
JNIEnv * GetEnv();
JavaVM * GetJVM();

jmethodID GetMethodID(JNIEnv * env, jobject obj, char const * name, char const * signature);
jmethodID GetStaticMethodID(JNIEnv * env, jclass clazz, char const * name, char const * signature);
jmethodID GetConstructorID(JNIEnv * env, jclass clazz, char const * signature);

// Result value should be DeleteGlobalRef`ed by caller
jclass GetGlobalClassRef(JNIEnv * env, char const * s);
std::string ToNativeString(JNIEnv * env, jstring str);
// Converts UTF-8 array to native UTF-8 string. Result differs from simple GetStringUTFChars call for characters greater than U+10000,
// since jni uses modified UTF (MUTF-8) for strings.
jstring ToJavaString(JNIEnv * env, char const * s);
inline jstring ToJavaString(JNIEnv * env, std::string const & s)
{
  return ToJavaString(env, s.c_str());
}

jclass GetStringClass(JNIEnv * env);
char const * GetStringClassName();

using TScopedLocalRef = ScopedLocalRef<jobject>;
using TScopedLocalClassRef = ScopedLocalRef<jclass>;
using TScopedLocalObjectArrayRef = ScopedLocalRef<jobjectArray>;
using TScopedLocalIntArrayRef = ScopedLocalRef<jintArray>;
using TScopedLocalByteArrayRef = ScopedLocalRef<jbyteArray>;

template<typename TIt, typename TToJavaFn>
jobjectArray ToJavaArray(JNIEnv * env, jclass clazz, TIt begin, TIt end, size_t const size, TToJavaFn && toJavaFn)
{
  jobjectArray jArray = env->NewObjectArray((jint) size, clazz, 0);
  size_t i = 0;
  for (auto it = begin; it != end; ++it)
  {
    TScopedLocalRef jItem(env, toJavaFn(env, *it));
    env->SetObjectArrayElement(jArray, i, jItem.get());
    ++i;
  }

  return jArray;
}

void DumpDalvikReferenceTables();
}  // namespace jni
