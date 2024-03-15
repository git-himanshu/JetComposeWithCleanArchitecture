import com.example.jetcomposewithcleanarchitecture.ConfigurationData
import com.example.jetcomposewithcleanarchitecture.Libs
import com.example.jetcomposewithcleanarchitecture.Versions

plugins {
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = ConfigurationData.NAME_SPACE
    compileSdk = ConfigurationData.COMPILE_SDK

    defaultConfig {
        applicationId = ConfigurationData.APPLICATION_ID
        minSdk = ConfigurationData.MIN_SDK
        targetSdk = ConfigurationData.TARGET_SDK
        versionCode = ConfigurationData.VERSION_CODE
        versionName = ConfigurationData.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.KOTLIN_COMPILER_EXTENSION_VERSION
    }
}

dependencies {
    implementation(platform(Libs.AndroidX.Compose.BOM))
    implementation(Libs.AndroidX.Compose.MATERIAL_3)
    implementation(Libs.Google.Hilt.HILT_ANDROID)
    implementation(project(":core:network"))
    kapt(Libs.Google.Hilt.HILT_ANDROID_COMPILER)
    implementation(Libs.AndroidX.Hilt.HILT_NAVIGATION_COMPOSE)
    implementation(Libs.AndroidX.Lifecycle.RUN_TIME_COMPOSE)
    debugImplementation(Libs.AndroidX.Compose.UI_TOOLING)
    implementation(project(":feature:bikeNetwork:presentation"))
    implementation(project(":feature:bikeNetwork:domain"))
    implementation(project(":feature:bikeNetwork:data"))
    implementation(project(":core:uiWidgets"))
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}