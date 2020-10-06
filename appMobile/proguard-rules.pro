# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

##---------------Begin: proguard configuration for navigation component  ----------
#[ https://stackoverflow.com/a/50378828/954752 ]
#to use argType in the navigation XML
# and prevent R8 from removing the model

-keep class io.android.projectx.presentation.base.model.UserView
##---------------End: proguard configuration for navigation component  ----------


##---------------Begin: proguard configuration for crashlytics  ----------

-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.

-keep class com.google.firebase.crashlytics.** { *; }
-dontwarn com.google.firebase.crashlytics.**

##---------------End: proguard configuration for crashlytics  ----------


-keep class kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoaderImpl

-keepclassmembers class kotlin.Metadata {
    public <methods>;
}


# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

##---------------Begin: proguard configuration for Moshi  ----------
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}

-keep @com.squareup.moshi.JsonQualifier interface *

# Enum field names are used by the integrated EnumJsonAdapter.
# values() is synthesized by the Kotlin compiler and is used by EnumJsonAdapter indirectly
# Annotate enums with @JsonClass(generateAdapter = false) to use them with Moshi.
-keepclassmembers @com.squareup.moshi.JsonClass class * extends java.lang.Enum {
    <fields>;
    **[] values();
}

-dontwarn java.lang.reflect.**
##---------------End: proguard configuration for Moshi  ----------