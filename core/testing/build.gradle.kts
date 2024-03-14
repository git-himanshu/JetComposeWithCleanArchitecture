import com.example.jetcomposewithcleanarchitecture.ConfigurationData
import com.example.jetcomposewithcleanarchitecture.Libs
import com.example.jetcomposewithcleanarchitecture.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.testing"
    compileSdk = ConfigurationData.COMPILE_SDK

    defaultConfig {
        minSdk = ConfigurationData.MIN_SDK
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Versions.SOURCE_COMPATIBILITY
        targetCompatibility = Versions.TARGET_COMPATIBILITY
    }
    kotlinOptions {
        jvmTarget = Versions.JVM_TARGET
    }
}

dependencies {
    implementation(project(":feature:bikeNetwork:domain"))
    implementation(project(":core:network"))
    implementation(Libs.JUnit.JUNIT)
    implementation(Libs.AndroidTest.COROUTINE_TEST)
    implementation(Libs.MockK.MOCKK)
    implementation(Libs.MockK.MOCKK_AGENT)
}