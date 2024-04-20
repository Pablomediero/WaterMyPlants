package pmediero.com

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import pmediero.com.core.di.mainModule
import pmediero.com.core.di.realmModule
import pmediero.com.features.plant.di.plantModule
import pmediero.com.features.plant.domain.useCase.SchedulePlantUpdateUseCase

class WaterMyPlantApp : Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        SchedulePlantUpdateUseCase().schedulePlantUpdate(applicationContext)
        startKoin {
            androidLogger()
            androidContext(this@WaterMyPlantApp)
            modules(
                realmModule,
                mainModule,
                plantModule
            )
        }
    }
}