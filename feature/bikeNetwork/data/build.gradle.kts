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
    namespace = "com.example.feature.bikeNetwork.data"
    compileSdk = ConfigurationData.compileSdk
    defaultConfig {
        minSdk = ConfigurationData.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
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
    implementation(Libs.Retrofit.converterGson)
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":feature:bikeNetwork:domain"))

    testImplementation(Libs.JUnit.jUnit)
    androidTestImplementation(Libs.AndroidTest.extJUnit)
    androidTestImplementation(platform(Libs.AndroidX.Compose.bom))
    testImplementation(Libs.AndroidTest.coroutinesTest)
    testImplementation(Libs.MockK.mockk)
    testImplementation(Libs.MockK.mockkAgent)
    testImplementation(project(":core:testing"))
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}