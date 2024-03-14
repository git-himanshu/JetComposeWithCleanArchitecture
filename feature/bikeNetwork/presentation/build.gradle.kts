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
    namespace = "com.example.feature.bikeNetwork.presentation"
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
}

dependencies {
    implementation(platform(Libs.AndroidX.Compose.BOM))
    implementation(Libs.AndroidX.Compose.MATERIAL_3)
    implementation(Libs.Google.Hilt.HILT_ANDROID)
    kapt(Libs.Google.Hilt.HILT_ANDROID_COMPILER)
    implementation(Libs.AndroidX.Hilt.HILT_NAVIGATION_COMPOSE)
    implementation(Libs.AndroidX.Lifecycle.RUN_TIME_COMPOSE)
    debugImplementation(Libs.AndroidX.Compose.UI_TOOLING)
    implementation(project(":feature:bikeNetwork:domain"))
    implementation(project(":core:uiWidgets"))
    implementation(project(":core:common"))

    testImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:testing"))
    testImplementation(Libs.JUnit.JUNIT)
    androidTestImplementation(Libs.AndroidTest.EXT_JUNIT)
    androidTestImplementation(platform(Libs.AndroidX.Compose.BOM))
    androidTestImplementation(Libs.AndroidTest.COMPOSE_TEST_JUNIT_4)
    debugImplementation(Libs.AndroidTest.COMPOSE_TEST_UI_TOOLING)
    debugImplementation(Libs.AndroidTest.COMPOSE_TEST_MANIFEST)
    testImplementation(Libs.AndroidTest.COROUTINE_TEST)
    testImplementation(Libs.MockK.MOCKK)
    testImplementation(Libs.MockK.MOCKK_AGENT)
    androidTestImplementation(Libs.MockK.MOCKK_ANDROID)
    androidTestImplementation(Libs.MockK.MOCKK_AGENT)
}

kapt {
    correctErrorTypes = true
}