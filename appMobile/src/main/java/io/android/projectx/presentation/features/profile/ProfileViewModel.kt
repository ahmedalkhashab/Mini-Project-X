package io.android.projectx.presentation.features.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.android.projectx.presentation.base.SessionManager
import io.android.projectx.presentation.base.model.UserView
import io.android.projectx.presentation.base.state.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    val profile: LiveData<AuthResource<UserView?>> = sessionManager.cachedUser

    fun getUser() = sessionManager.getUser()

}