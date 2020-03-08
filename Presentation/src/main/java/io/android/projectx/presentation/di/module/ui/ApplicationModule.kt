package io.android.projectx.presentation.di.module.ui

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.android.projectx.presentation.R

@Module(includes = [FlavorModule::class, GlideModule::class])
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: Application): Context
}

@Module
internal object GlideModule {

    @Provides
    @JvmStatic
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error_background)
    }

    @Provides
    @JvmStatic
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

}