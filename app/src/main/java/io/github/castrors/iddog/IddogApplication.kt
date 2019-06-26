package io.github.castrors.iddog

import android.app.Application
import com.orhanobut.hawk.Hawk
import io.github.castrors.iddog.injection.DataModule.base
import io.github.castrors.iddog.injection.DataModule.dataModule
import org.koin.core.context.startKoin
import timber.log.Timber

class IddogApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Hawk.init(this).build()
        startKoin {
            modules(listOf(base, dataModule))
        }
    }
}