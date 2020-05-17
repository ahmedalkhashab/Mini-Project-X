package io.android.projectx.presentation.features.usermanagement.login

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import io.android.projectx.androidextensions.afterTextChanged
import io.android.projectx.androidextensions.getSupportColor
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.BaseFragment
import io.android.projectx.presentation.base.model.CountryView
import io.android.projectx.presentation.base.state.Resource
import io.android.projectx.presentation.base.state.Resource.Status.AuthStatus
import io.android.projectx.presentation.extensions.updateVisibility
import io.android.projectx.presentation.features.usermanagement.UserManagementViewModel
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.login
import kotlinx.android.synthetic.main.login_fragment.progressbar
import timber.log.Timber

class LoginFragment : BaseFragment(R.layout.login_fragment) {

    private val viewModel: UserManagementViewModel by appViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
        handleUsernameUi()
        handlePasswordUi()
        skip.setOnClickListener { navigator.toBrowseRecipesScreen(requireActivity()) }
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

        viewModel.getCachedUser().observe(viewLifecycleOwner, Observer {
            progressbar.updateVisibility(it.status)
            when (it.status) {
                Resource.Status.LOADING -> {
                    Timber.d("login: User LOADING")
                }
                Resource.Status.ERROR -> {
                    Timber.d("login: User ERROR")
                    handleError(it.throwable)
                }
                AuthStatus.AUTHENTICATED -> {
                    Timber.d("login: AUTHENTICATED: %s", it.data?.mobile)
                }
                AuthStatus.ANONYMOUS -> {
                    Timber.d("login: NOT_AUTHENTICATED: %s", it.data?.userStatus)
                    handleError(it.throwable)
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
                viewModel.login(
                    "+966",
                    username.editText?.text.toString(),
                    password.editText?.text.toString()
                )
            }
        }
    }

}