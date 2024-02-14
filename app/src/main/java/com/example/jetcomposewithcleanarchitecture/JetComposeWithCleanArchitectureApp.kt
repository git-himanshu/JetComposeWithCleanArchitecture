package com.example.jetcomposewithcleanarchitecture

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@Singleton
@HiltAndroidApp
class JetComposeWithCleanArchitectureApp : Application()