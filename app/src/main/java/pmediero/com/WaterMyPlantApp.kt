package pmediero.com

import android.app.Application
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import pmediero.com.core.model.realm.PlantEntity
import pmediero.com.features.addplant.di.addPlantModule
import pmediero.com.features.home.di.homeModule

class WaterMyPlantApp : Application() {
    companion object {
        lateinit var realm: Realm
    }
    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    PlantEntity::class
                )
            )
        )
        startKoin {
            androidLogger()
            androidContext(this@WaterMyPlantApp)
            modules(
                addPlantModule,
                homeModule
            )
        }
    }
}