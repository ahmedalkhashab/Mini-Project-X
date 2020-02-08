package io.android.projectx.presentation.di.module

import dagger.Binds
import dagger.Module
import io.android.projectx.data.RecipesDataRepository
import io.android.projectx.domain.repository.RecipesRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: RecipesDataRepository): RecipesRepository
}