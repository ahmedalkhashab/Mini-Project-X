package io.android.projectx.presentation.di.module.ui.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.android.projectx.presentation.base.BaseDialogFragment
import io.android.projectx.presentation.di.module.ui.viewmodel.AuthViewModelModule
import io.android.projectx.presentation.features.language.LanguageFragment
import io.android.projectx.presentation.features.menu.MenuFragment
import io.android.projectx.presentation.features.profile.ProfileFragment

@Module
abstract class AuthFragmentBuildersModule {

    @ContributesAndroidInjector(modules = [AuthViewModelModule::class])
    abstract fun contributesMenuFragment(): MenuFragment

    @ContributesAndroidInjector(modules = [AuthViewModelModule::class])
    abstract fun contributesProfileFragment(): ProfileFragment

    @ContributesAndroidInjector(modules = [AuthViewModelModule::class])
    abstract fun contributesLanguageFragment(): LanguageFragment

    @ContributesAndroidInjector(modules = [AuthViewModelModule::class])
    abstract fun contributesBaseDialogFragment(): BaseDialogFragment

}