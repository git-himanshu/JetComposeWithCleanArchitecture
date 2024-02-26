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
    compileSdk = ConfigurationData.compileSdk

    defaultConfig {
        minSdk = ConfigurationData.minSdk

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

    androidTestImplementation(platform(Libs.AndroidX.Compose.bom))
    androidTestImplementation(Libs.AndroidTest.composeTestJunit4)
    debugImplementation(Libs.AndroidTest.composeTestUiTooling)
    debugImplementation(Libs.AndroidTest.composeTestManifest)
}

kapt {
    correctErrorTypes = true
}