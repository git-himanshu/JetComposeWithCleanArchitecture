package com.example.jetcomposewithcleanarchitecture

import org.gradle.api.JavaVersion

object Versions {
    const val jvmTarget = "1.8"
    const val kotlinCompilerExtensionVersion = "1.5.9"
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8
}

object Libs {
    object AndroidX {
        object Compose {
            const val bom = "androidx.compose:compose-bom:2023.08.00"
            const val material3 = "androidx.compose.material3:material3"
            const val uiTooling = "androidx.compose.ui:ui-tooling"
        }

        object Hilt {
            const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.1.0"
        }

        object Lifecycle {
            const val runtimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0"
        }

    }

    object Google {
        object Hilt {
            const val hiltAndroid = "com.google.dagger:hilt-android:2.50"
            const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:2.48"
        }

    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofitClient = "com.squareup.retrofit2:retrofit:$version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object AndroidTest {
        const val composeTestJunit4 = "androidx.compose.ui:ui-test-junit4"
        const val composeTestUiTooling = "androidx.compose.ui:ui-tooling"
        const val composeTestManifest = "androidx.compose.ui:ui-test-manifest"
        const val extJUnit = "androidx.test.ext:junit:1.1.5"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test"
    }

    object MockK {
        private const val version = "1.13.9"
        const val mockk = "io.mockk:mockk:${version}"
        const val mockkAgent = "io.mockk:mockk-agent:${version}"
        const val mockkAndroid = "io.mockk:mockk-android:${version}"
    }

    object JUnit {
        const val jUnit = "junit:junit:4.13.2"
    }
}