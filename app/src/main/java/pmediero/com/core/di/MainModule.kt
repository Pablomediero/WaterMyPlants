package pmediero.com.core.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pmediero.com.core.domain.CheckPlantExistUseCase
import pmediero.com.features.plant.data.PlantRepositoryImpl
import pmediero.com.features.plant.domain.repository.PlantRepository
import pmediero.com.navigation.MainViewModel

val mainModule = module {
    domainModule()
    dataModule()
    presentationModule()
}

private fun Module.domainModule() {
    singleOf(::CheckPlantExistUseCase)
}

private fun Module.dataModule() {
    singleOf(::PlantRepositoryImpl) bind PlantRepository::class
}
private fun Module.presentationModule() {
    viewModelOf(::MainViewModel)
}
