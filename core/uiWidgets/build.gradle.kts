import com.example.jetcomposewithcleanarchitecture.ConfigurationData
import com.example.jetcomposewithcleanarchitecture.Libs
import com.example.jetcomposewithcleanarchitecture.Versions

plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.core.uiWidgets"
    compileSdk = ConfigurationData.COMPILE_SDK

    defaultConfig {
        minSdk = ConfigurationData.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(platform(Libs.AndroidX.Compose.BOM))
    implementation(Libs.AndroidX.Compose.MATERIAL_3)
    implementation(Libs.Google.Hilt.HILT_ANDROID)
    kapt(Libs.Google.Hilt.HILT_ANDROID_COMPILER)
    implementation(Libs.AndroidX.Hilt.HILT_NAVIGATION_COMPOSE)
    implementation(Libs.AndroidX.Lifecycle.RUN_TIME_COMPOSE)
    debugImplementation(Libs.AndroidX.Compose.UI_TOOLING)

    androidTestImplementation(platform(Libs.AndroidX.Compose.BOM))
    androidTestImplementation(Libs.AndroidTest.COMPOSE_TEST_JUNIT_4)
    debugImplementation(Libs.AndroidTest.COMPOSE_TEST_UI_TOOLING)
    debugImplementation(Libs.AndroidTest.COMPOSE_TEST_MANIFEST)
}

kapt {
    correctErrorTypes = true
}