package com.study.spaceflightnewsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SpaceFlightApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}