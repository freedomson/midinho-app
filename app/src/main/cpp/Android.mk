LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := liblisbongames-bootstrap
LOCAL_SRC_FILES := lisbongames-bootstrap-zip.S lisbongames-bootstrap.c
include $(BUILD_SHARED_LIBRARY)
