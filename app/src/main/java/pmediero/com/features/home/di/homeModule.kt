package pmediero.com.features.home.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pmediero.com.features.home.domain.GetPlantsUseCase
import pmediero.com.features.home.presentation.HomeViewModel

val homeModule = module {
    presentationModule()
    domainModule()
}

private fun Module.domainModule() {
    factoryOf(::GetPlantsUseCase)
}
private fun Module.presentationModule() {
    viewModelOf(::HomeViewModel)
}