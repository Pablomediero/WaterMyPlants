package pmediero.com.core.di

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.model.realm.PlantEntity

val realmModule = module {
    initDatabase()
    dataModule()
}

private fun Module.initDatabase() {
    single {
        Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    PlantEntity::class
                )
            )
        )
    }
}
private fun Module.dataModule() {

    factoryOf(::PlantRepository)
}