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
    namespace = "com.example.domain"
    compileSdk = ConfigurationData.COMPILE_SDK
    defaultConfig {
        minSdk = ConfigurationData.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(Libs.Google.Hilt.HILT_ANDROID)
    kapt(Libs.Google.Hilt.HILT_ANDROID_COMPILER)
    implementation(Libs.AndroidX.Hilt.HILT_NAVIGATION_COMPOSE)
    implementation(project(":core:common"))


    testImplementation(Libs.JUnit.JUNIT)
    testImplementation(Libs.AndroidTest.COROUTINE_TEST)
    testImplementation(Libs.MockK.MOCKK)
    testImplementation(Libs.MockK.MOCKK_AGENT)
    testImplementation(project(":core:testing"))
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}