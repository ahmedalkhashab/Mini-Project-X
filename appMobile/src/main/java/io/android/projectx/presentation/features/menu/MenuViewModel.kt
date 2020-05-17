package io.android.projectx.presentation.features.menu

import androidx.lifecycle.ViewModel
import io.android.projectx.presentation.base.SessionManager
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    fun logOut() {
        sessionManager.logOut()
    }

}