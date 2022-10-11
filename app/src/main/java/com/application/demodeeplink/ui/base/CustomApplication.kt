package com.application.demodeeplink.ui.base

import android.app.Application
import io.branch.referral.Branch

class CustomApplication : Application() {

    companion object {
        lateinit var application: Application
        fun getInstance(): Application {
            return application
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        // Branch logging for debugging
        Branch.enableLogging()
        // Branch object initialization
        Branch.getAutoInstance(application)
    }
}