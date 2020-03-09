#!/usr/bin/env bash
#for dev-release apk
LATEST_APK_DEV=$(ls -lrt ./appMobile/build/outputs/apk/dev/release/*.apk | tail -1 | awk -F" " '{ print $9 }') #Pick the latest dev build apk.
FILE_NAME_DEV=$(basename $LATEST_APK_DEV .apk)".apk"
FILE_TITLE_DEV=$(basename $LATEST_APK_DEV .apk) #optional -- For changelog title.

#for Staging-release apk
LATEST_APK_STAGING=$(ls -lrt ./appMobile/build/outputs/apk/staging/release/*.apk | tail -1 | awk -F" " '{ print $9 }') #Pick the latest Staging build apk.
FILE_NAME_STAGING=$(basename $LATEST_APK_STAGING .apk)".apk"
FILE_TITLE_STAGING=$(basename $LATEST_APK_STAGING .apk) #optional -- For changelog title.

#for production-release apk
LATEST_APK_PRODUCTION=$(ls -lrt ./appMobile/build/outputs/apk/production/release/*.apk | tail -1 | awk -F" " '{ print $9 }') #Pick the latest production build apk.
FILE_NAME_PRODUCTION=$(basename $LATEST_APK_PRODUCTION .apk)".apk"
FILE_TITLE_PRODUCTION=$(basename $LATEST_APK_PRODUCTION .apk) #optional -- For changelog title.

BUILD_DATE=`date +%Y-%m-%d` #optional -- For changelog title.