package com.test.assignment.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.test.assignment.util.InternetUtil
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application:Application(),Application.ActivityLifecycleCallbacks,LifecycleObserver {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(listOf(viewModelModule, repositoryModule, apiModule,netModule))
        }

        //Check Status of Application
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        InternetUtil.init(this)
    }



    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        Log.e("State","onActivityCreated")
    }

    override fun onActivityStarted(p0: Activity) {
        Log.e("State","onActivityCreated")
    }

    override fun onActivityResumed(p0: Activity) {
        Log.e("State","onActivityCreated")
    }

    override fun onActivityPaused(p0: Activity) {
        Log.e("State","onActivityCreated")
    }

    override fun onActivityStopped(p0: Activity) {
        Log.e("State","onActivityCreated")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        Log.e("State","onActivityCreated")
    }

    override fun onActivityDestroyed(p0: Activity) {
        Log.e("State","onActivityDestroyed${p0}")
    }
}