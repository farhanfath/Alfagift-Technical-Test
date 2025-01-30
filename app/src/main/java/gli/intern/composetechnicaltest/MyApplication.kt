package gli.intern.composetechnicaltest

import android.app.Application
import gli.intern.composetechnicaltest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
            androidContext(this@MyApplication)
        }
    }
}