package com.example.jetcomposewithcleanarchitecture

import org.gradle.api.JavaVersion

object Versions {
    const val JVM_TARGET = "1.8"
    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.9"
    val SOURCE_COMPATIBILITY = JavaVersion.VERSION_1_8
    val TARGET_COMPATIBILITY = JavaVersion.VERSION_1_8
}

object Libs {
    object AndroidX {
        object Compose {
            const val BOM = "androidx.compose:compose-bom:2023.08.00"
            const val MATERIAL_3 = "androidx.compose.material3:material3"
            const val UI_TOOLING = "androidx.compose.ui:ui-tooling"
        }

        object Hilt {
            const val HILT_NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:1.1.0"
        }

        object Lifecycle {
            const val RUN_TIME_COMPOSE = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0"
        }

    }

    object Google {
        object Hilt {
            const val HILT_ANDROID = "com.google.dagger:hilt-android:2.50"
            const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:2.48"
        }

    }

    object Retrofit {
        private const val VERSION = "2.9.0"
        const val RETROFIT_CLIENT = "com.squareup.retrofit2:retrofit:$VERSION"
        const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:$VERSION"
    }

    object AndroidTest {
        const val COMPOSE_TEST_JUNIT_4 = "androidx.compose.ui:ui-test-junit4"
        const val COMPOSE_TEST_UI_TOOLING = "androidx.compose.ui:ui-tooling"
        const val COMPOSE_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"
        const val EXT_JUNIT = "androidx.test.ext:junit:1.1.5"
        const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test"
    }

    object MockK {
        private const val VERSION = "1.13.9"
        const val MOCKK = "io.mockk:mockk:${VERSION}"
        const val MOCKK_AGENT = "io.mockk:mockk-agent:${VERSION}"
        const val MOCKK_ANDROID = "io.mockk:mockk-android:${VERSION}"
    }

    object JUnit {
        const val JUNIT = "junit:junit:4.13.2"
    }
}