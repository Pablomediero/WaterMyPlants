package pmediero.com.features.addplant.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.features.addplant.domain.AddPlantUseCase
import pmediero.com.features.addplant.domain.FilterWateringDaysUseCase
import pmediero.com.features.addplant.presentation.AddPlantViewModel

val addPlantModule = module {
    domainModule()
    dataModule()
    presentationModule()
}

private fun Module.domainModule() {
    factoryOf(::FilterWateringDaysUseCase)
    factoryOf(::AddPlantUseCase)
}
private fun Module.dataModule() {
    factoryOf(::PlantRepository)
}
private fun Module.presentationModule() {
    viewModelOf(::AddPlantViewModel)
}
