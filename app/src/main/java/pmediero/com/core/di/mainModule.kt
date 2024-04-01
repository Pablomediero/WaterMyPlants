package pmediero.com.core.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.domain.CheckPlantExistUseCase
import pmediero.com.navigation.MainViewModel

val mainModule = module {
    domainModule()
    dataModule()
    presentationModule()
}

private fun Module.domainModule() {
    factoryOf(::CheckPlantExistUseCase)
}

private fun Module.dataModule() {
    factoryOf(::PlantRepository)
}
private fun Module.presentationModule() {
    viewModelOf(::MainViewModel)
}
