package io.android.projectx.presentation.di.module.ui.service

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.android.projectx.presentation.features.cloudmessaging.CloudMessagingService

@Module
abstract class ServiceBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCloudMessagingService(): CloudMessagingService

}