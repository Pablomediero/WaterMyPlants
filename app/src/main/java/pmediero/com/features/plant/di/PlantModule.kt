package pmediero.com.features.plant.di

import android.os.Build
import androidx.annotation.RequiresApi
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pmediero.com.features.plant.data.PlantRepositoryImpl
import pmediero.com.features.plant.data.local.localsource.PlantLocalSource
import pmediero.com.features.plant.data.local.localsource.PlantLocalSourceImpl
import pmediero.com.features.plant.data.notification.scheduler.NotificationScheduler
import pmediero.com.features.plant.domain.repository.PlantRepository
import pmediero.com.features.plant.domain.useCase.AddPlantUseCase
import pmediero.com.features.plant.domain.useCase.FilterWateringDaysUseCase
import pmediero.com.features.plant.domain.useCase.GetFilteredNotificationsUseCase
import pmediero.com.features.plant.domain.useCase.GetFilteredPlantsUseCase
import pmediero.com.features.plant.domain.useCase.GetPlantByIdUseCase
import pmediero.com.features.plant.domain.useCase.UpdateWateredPlantUseCase
import pmediero.com.features.plant.presentation.addeditplant.AddEditPlantViewModel
import pmediero.com.features.plant.presentation.detailplant.DetailViewModel
import pmediero.com.features.plant.presentation.home.HomeViewModel
import pmediero.com.features.plant.presentation.notification.NotificationViewModel

@RequiresApi(Build.VERSION_CODES.O)
val plantModule = module {
    domainModule()
    dataModule()
    presentationModule()
}

private fun Module.domainModule() {
    singleOf(::FilterWateringDaysUseCase)
    singleOf(::AddPlantUseCase)
    singleOf(::GetFilteredPlantsUseCase)
    singleOf(::UpdateWateredPlantUseCase)
    singleOf(::GetPlantByIdUseCase)
    singleOf(::GetFilteredNotificationsUseCase)
}

private fun Module.dataModule() {
    singleOf(::PlantRepositoryImpl) bind PlantRepository::class
    singleOf(::PlantLocalSourceImpl) bind PlantLocalSource::class
    singleOf(::NotificationScheduler)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun Module.presentationModule() {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::AddEditPlantViewModel)
    viewModelOf(::NotificationViewModel)
}
