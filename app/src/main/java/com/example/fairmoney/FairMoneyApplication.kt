package com.example.fairmoney

import android.app.Application
import android.content.ComponentCallbacks2
import com.facebook.drawee.backends.pipeline.Fresco

class FairMoneyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level != ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            val imagePipeline = Fresco.getImagePipeline()
            imagePipeline.clearCaches()
        }
    }
}
