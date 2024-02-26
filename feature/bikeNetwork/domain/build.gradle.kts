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
    namespace = "com.example.feature.bikeNetwork.domain"
    compileSdk = ConfigurationData.compileSdk

    compileOptions {
        sourceCompatibility = Versions.sourceCompatibility
        targetCompatibility = Versions.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
}

dependencies {
    implementation(Libs.Google.Hilt.hiltAndroid)
    kapt(Libs.Google.Hilt.hiltAndroidCompiler)
    implementation(Libs.AndroidX.Hilt.hiltNavigationCompose)
    implementation(project(":core:common"))
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}