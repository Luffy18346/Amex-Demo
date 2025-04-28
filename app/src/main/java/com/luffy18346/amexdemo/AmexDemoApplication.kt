package com.luffy18346.amexdemo

import android.app.Application
import com.luffy18346.amexdemo.di.appModule
import com.luffy18346.amexdemo.di.domainModule
import com.luffy18346.amexdemo.di.networkModule
import com.luffy18346.amexdemo.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class AmexDemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@AmexDemoApplication)
            modules(networkModule, repositoryModule, domainModule, appModule)
        }
    }
}