package io.android.projectx.presentation.di.module

import dagger.Binds
import dagger.Module
import io.android.projectx.data.features.recipes.RecipesDataRepository
import io.android.projectx.data.features.restaurants.RestaurantsDataRepository
import io.android.projectx.domain.features.recipes.repository.RecipesRepository
import io.android.projectx.domain.features.restaurants.repository.RestaurantsRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: RecipesDataRepository): RecipesRepository

    @Binds
    abstract fun bindRestaurantsRepository(dataRepository: RestaurantsDataRepository): RestaurantsRepository
}