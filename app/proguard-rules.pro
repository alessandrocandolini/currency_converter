# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/alessandro.candolini/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

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

# General Options
# Refer to: https://www.guardsquare.com/en/proguard/manual/usage#shrinkingoptions
-verbose

# dagger-android 2.11
# ErrorProne annotations have no use at runtime, so it's fine to ignore them in Proguard.
# Refer to: https://github.com/google/dagger/issues/645
-dontwarn com.google.errorprone.annotations.*

# Duplication of http apache classes coming from useLibrary 'org.apache.http.legacy'
# Refer to: https://issuetracker.google.com/issues/37070898
-dontnote android.net.http.*
-dontnote org.apache.commons.codec.**
-dontnote org.apache.http.**

# Remove logs
# Refer to: https://www.guardsquare.com/en/proguard/manual/examples#logging
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

# Preverification Options
# Preverification is irrelevant for the dex compiler and the Dalvik VM, so we can switch it off with the -dontpreverify option.
-dontpreverify

# -overloadaggressively can't be used: Dalvik VM can't handle overloaded static fields.