// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    val applicationVersion by System.getProperties()
    id("com.android.application") version "$applicationVersion" apply false
    val kotlinAndroidVersion by System.getProperties()
    id("org.jetbrains.kotlin.android") version "$kotlinAndroidVersion" apply false
    val hiltAndroidVersion by System.getProperties()
    id("com.google.dagger.hilt.android") version "$hiltAndroidVersion" apply false
    val kotlinJvmVersion by System.getProperties()
    id("org.jetbrains.kotlin.jvm") version "$kotlinJvmVersion" apply false
    val androidLibraryVersion by System.getProperties()
    id("com.android.library") version "$androidLibraryVersion" apply false
}