package io.android.projectx.presentation.features.usermanagement.login

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import io.android.projectx.androidextensions.afterTextChanged
import io.android.projectx.androidextensions.getSupportColor
import io.android.projectx.androidextensions.showToastShort
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.BaseFragment
import io.android.projectx.presentation.base.model.CountryView
import io.android.projectx.presentation.base.model.UserView
import io.android.projectx.presentation.base.state.AuthResource.AuthStatus
import kotlinx.android.synthetic.main.login_fragment.*
import timber.log.Timber

class LoginFragment : BaseFragment(R.layout.login_fragment) {

    private val viewModel: LoginViewModel by appViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
        handleUsernameUi()
        handlePasswordUi()
    }

    private fun subscribeObservers() {
        viewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer
            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid
            val supportColor = requireContext().getSupportColor(
                if (loginState.isDataValid) R.color.colorAccent
                else R.color.colorDisabled
            )
            login.setBackgroundColor(supportColor)
            // display username error if exist
            if (loginState.usernameError != null
                && username.editText?.length() ?: 0 > 0
                && username.editText?.hasFocus() == false
            ) {
                username.error = getString(loginState.usernameError)
            }
            // display password error if exist
            if (loginState.passwordError != null && password.editText?.length() ?: 0 > 0) {
                password.error = getString(loginState.passwordError)
            }
        })

        // todo - Be certain if we need to remove previous observers - It will effect on BaseActivity and Authenticator
        viewModel.getCachedUser().removeObservers(viewLifecycleOwner)
        viewModel.getCachedUser().observe(viewLifecycleOwner, Observer {
            val userResource = it ?: return@Observer
            when (userResource.status) {
                AuthStatus.LOADING -> {
                    //showProgressBar(true)
                    loading.visibility = View.VISIBLE
                    Timber.d("login: User LOADING")
                }
                AuthStatus.ERROR -> {
                    //showProgressBar(false)
                    loading.visibility = View.GONE
                    Timber.d("login: User ERROR")
                    userResource.message?.let { message -> showLoginFailed(message) }
                }
                AuthStatus.AUTHENTICATED -> {
                    //showProgressBar(false)
                    loading.visibility = View.GONE
                    Timber.d("login: AUTHENTICATED: %s", userResource.data?.mobile)
                    onLoginSuccess(userResource.data!!)
                }
                AuthStatus.NOT_AUTHENTICATED -> {
                    //showProgressBar(false)
                    loading.visibility = View.GONE
                    Timber.d("login: NOT_AUTHENTICATED: %s", userResource.data?.userStatus)
                    //logout
                    // todo - update behaviour here
                    showLoginFailed("Like Error in this App : No Anonymous user here")
                }
            }
        })
    }

    private fun handleUsernameUi() {
        username.editText?.afterTextChanged {
            username.error = null
            viewModel.loginDataChanged(
                CountryView("Saudi Arabia", "+966"),
                username.editText?.text.toString(),
                password.editText?.text.toString()
            )
        }
    }

    private fun handlePasswordUi() {
        password.editText?.apply {
            afterTextChanged {
                password.error = null
                viewModel.loginDataChanged(
                    CountryView("Saudi Arabia", "+966"),
                    username.editText?.text.toString(),
                    password.editText?.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewModel.login(
                            "+966",
                            username.editText?.text.toString(),
                            password.editText?.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                viewModel.login(
                    "+966",
                    username.editText?.text.toString(),
                    password.editText?.text.toString()
                )
            }
        }
    }

    private fun onLoginSuccess(user: UserView) {
        // todo - Complete and destroy login screen once successful
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        context.showToastShort(errorString)
    }

    private fun showLoginFailed(errorString: String) {
        context.showToastShort(errorString)
    }

}