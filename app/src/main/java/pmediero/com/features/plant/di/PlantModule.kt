package pmediero.com.features.plant.di

import android.os.Build
import androidx.annotation.RequiresApi
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pmediero.com.features.plant.data.notification.scheduler.ScheduleNotificationPlant
import pmediero.com.features.plant.data.repository.PlantRepository
import pmediero.com.features.plant.domain.useCase.AddPlantUseCase
import pmediero.com.features.plant.domain.useCase.FilterWateringDaysUseCase
import pmediero.com.features.plant.domain.useCase.GetFilteredPlantsUseCase
import pmediero.com.features.plant.domain.useCase.GetPlantByIdUseCase
import pmediero.com.features.plant.domain.useCase.UpdateWateredPlantUseCase
import pmediero.com.features.plant.presentation.addeditplant.AddEditPlantViewModel
import pmediero.com.features.plant.presentation.detailplant.DetailViewModel
import pmediero.com.features.plant.presentation.home.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
val plantModule = module {
    domainModule()
    dataModule()
    presentationModule()
}

private fun Module.domainModule() {
    factoryOf(::FilterWateringDaysUseCase)
    factoryOf(::AddPlantUseCase)
    factoryOf(::GetFilteredPlantsUseCase)
    factoryOf(::UpdateWateredPlantUseCase)
    factoryOf(::GetPlantByIdUseCase)
}

private fun Module.dataModule() {
    factoryOf(::PlantRepository)
    factoryOf(::ScheduleNotificationPlant)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun Module.presentationModule() {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::AddEditPlantViewModel)
}
