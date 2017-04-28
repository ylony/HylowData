#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_ylofanclub_hylow_hylowdata_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Salut moi c'est Hylow :)";
    return env->NewStringUTF(hello.c_str());
}
extern "C" jstring Java_com_ylofanclub_hylow_hylowdata_MainActivity_stringFromJNI2(JNIEnv* env, jobject ) {
        std::string hello2 = "Salut moi c'est Hylow 2 :)";
        return env->NewStringUTF(hello2.c_str());
}


