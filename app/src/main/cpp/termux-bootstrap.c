#include <jni.h>
// MIDINHO add stdio.h
#include <stdio.h>

extern jbyte blob[];
extern int blob_size;

// MIDINHO
// JNIEXPORT jbyteArray JNICALL Java_com_termux_app_TermuxInstaller_getZip(JNIEnv *env, __attribute__((__unused__)) jobject This)
// {
//     jbyteArray ret = (*env)->NewByteArray(env, blob_size);
//     (*env)->SetByteArrayRegion(env, ret, 0, blob_size, blob);
//     return ret;
// }

JNIEXPORT void JNICALL Java_com_termux_app_TermuxInstaller_writeZipToFile(JNIEnv *env, __attribute__((__unused__)) jobject This, jstring jpath)
{
    const char *path = (*env)->GetStringUTFChars(env, jpath, NULL);
    if (path == NULL) {
        return;
    }

    FILE *fp = fopen(path, "wb");
    if (fp == NULL) {
        (*env)->ReleaseStringUTFChars(env, jpath, path);
        return;
    }

    fwrite(blob, 1, blob_size, fp);
    fclose(fp);

    (*env)->ReleaseStringUTFChars(env, jpath, path);
}
