package io.android.projectx.presentation.features.usermanagement

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.android.projectx.androidextensions.phone.formatNumberToE164
import io.android.projectx.androidextensions.phone.getCountryIso
import io.android.projectx.androidextensions.phone.isValidPhone
import io.android.projectx.extensions.phone.isValidSaudiMobile
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.SessionManager
import io.android.projectx.presentation.base.model.CountryView
import io.android.projectx.presentation.base.model.UserView
import io.android.projectx.presentation.base.state.AuthResource
import io.android.projectx.presentation.features.usermanagement.login.LoginFormState
import javax.inject.Inject

class UserManagementViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    override fun onCleared() {
        super.onCleared()
    }

    fun getCachedUser(): LiveData<AuthResource<UserView?>> = sessionManager.cachedUser

    fun login(countryCode: String, mobile: String, password: String) {
        // can be launched in a separate asynchronous job
        sessionManager.login(countryCode, mobile, password)
    }

    fun loginDataChanged(country: CountryView, mobile: String, password: String) {
        if (!isUserNameValid(country, mobile)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(country: CountryView, mobile: String): Boolean {
        return when (country.phoneCode) {
            "+966" -> {
                if (isValidSaudiMobile(mobile)) {
                    val countryIso = getCountryIso(country.name)
                    val formatNumberE164 = formatNumberToE164(mobile, countryIso) ?: ""
                    (isValidPhone(mobile, countryIso)
                            && Patterns.PHONE.matcher(formatNumberE164).matches())
                } else false
            }
            else -> false
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

}