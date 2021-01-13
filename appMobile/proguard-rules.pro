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

#okhttp
-keepclassmembers class * implements javax.net.ssl.SSLSocketFactory {
    private final javax.net.ssl.SSLSocketFactory delegate;
}

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
-keep class io.android.projectx.remote.features.usermanagement.model.response.LoginWrapper { *; }
-keep class io.android.projectx.remote.features.usermanagement.model.response.CloudRegistrationWrapper { *; }
-keep class io.android.projectx.remote.features.usermanagement.model.request.EmailCredentialRequest { *; }
-keep class io.android.projectx.remote.features.usermanagement.model.request.MobileCredentialRequest { *; }
-keep class io.android.projectx.remote.features.usermanagement.model.request.MobileNumber { *; }
-keep class io.android.projectx.remote.features.usermanagement.model.request.ResetPasswordCredentialRequest { *; }
-keep class io.android.projectx.remote.features.usermanagement.model.request.UserNameCredentialRequest { *; }
-keep class io.android.projectx.remote.features.usermanagement.model.request.cloudmessaging.TokenCmRequestModel { *; }
-keep class io.android.projectx.remote.features.usermanagement.model.UserModel { *; }
-keep class io.android.projectx.remote.features.usermanagement.model.UserStatusModel { *; }
-keep class io.android.projectx.remote.features.recipes.model.AuthorModel { *; }
-keep class io.android.projectx.remote.features.recipes.model.RecipeModel { *; }
-keep class io.android.projectx.remote.features.recipes.model.response.RecipesResponseModel { *; }
-keep class io.android.projectx.remote.features.restaurants.model.response.RestaurantsResponseModel { *; }
-keep class io.android.projectx.remote.features.restaurants.model.RestaurantModel { *; }
-keep class io.android.projectx.remote.base.model.ServerError { *; }
-keep class io.android.projectx.remote.base.model.RemoteException { *; }
##---------------End: proguard configuration for Moshi  ----------