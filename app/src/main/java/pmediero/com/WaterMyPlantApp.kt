package pmediero.com

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import pmediero.com.features.addplant.di.addPlantModule

class WaterMyPlantApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WaterMyPlantApp)
            modules(addPlantModule)
        }
    }
}