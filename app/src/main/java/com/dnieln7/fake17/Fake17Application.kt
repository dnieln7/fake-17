package com.dnieln7.fake17

import android.app.Application

class Fake17Application : Application() {
    val serviceLocator = ServiceLocator(this)
}