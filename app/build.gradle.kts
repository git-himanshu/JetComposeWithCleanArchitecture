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
    namespace = ConfigurationData.nameSpace
    compileSdk = ConfigurationData.compileSdk

    defaultConfig {
        applicationId = ConfigurationData.applicationId
        minSdk = ConfigurationData.minSdk
        targetSdk = ConfigurationData.targetSdk
        versionCode = ConfigurationData.versionCode
        versionName = ConfigurationData.versionName

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
        sourceCompatibility = Versions.sourceCompatibility
        targetCompatibility = Versions.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
        }
    }
}

dependencies {
    implementation(platform(Libs.AndroidX.Compose.bom))
    implementation(Libs.AndroidX.Compose.material3)
    implementation(Libs.Google.Hilt.hiltAndroid)
    kapt(Libs.Google.Hilt.hiltAndroidCompiler)
    implementation(Libs.AndroidX.Hilt.hiltNavigationCompose)
    implementation(Libs.AndroidX.Lifecycle.runtimeCompose)
    debugImplementation(Libs.AndroidX.Compose.uiTooling)
    implementation(project(":feature:bikeNetwork:presentation"))
    implementation(project(":feature:bikeNetwork:domain"))
    implementation(project(":feature:bikeNetwork:data"))
    implementation(project(":core:uiWidgets"))
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}