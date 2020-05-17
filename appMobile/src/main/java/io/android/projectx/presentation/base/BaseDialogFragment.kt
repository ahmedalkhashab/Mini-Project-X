package io.android.projectx.presentation.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerDialogFragment
import io.android.projectx.androidextensions.LocalizationHandler
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.state.Resource
import kotlinx.android.synthetic.main.view_dialog_full_screen.*
import javax.inject.Inject

open class BaseDialogFragment(
    @LayoutRes private val layoutRes: Int = 0,
    @DrawableRes private val iconRes: Int = android.R.drawable.ic_dialog_alert,
    private val message: String,
    private val textOk: String? = null,
    private val textNo: String? = null,
    private val dialogListener: DialogListener
) : DaggerDialogFragment() {

    @Inject
    lateinit var baseViewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(LocalizationHandler.onAttach(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.view_dialog_full_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerListeners()
        initUi()
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        requireActivity().let { LocalizationHandler.onAttach(it) }
    }

    inline fun <reified VM : ViewModel> appViewModels() = viewModels<VM> { baseViewModelFactory }
    inline fun <reified VM : ViewModel> appActivityViewModels() =
        activityViewModels<VM> { baseViewModelFactory }

    private fun registerListeners() {
        btnOk.setOnClickListener {
            //send back data to PARENT fragment using callback
            dialogListener.onDataReceived(Resource.success(true))
            // Now dismiss the fragment
            dismiss()
        }
        btnNo.setOnClickListener {
            //send back data to PARENT fragment using callback
            dialogListener.onDataReceived(Resource.success(false))
            // Now dismiss the fragment
            dismiss()
        }
    }

    private fun initUi() {
        icon.setImageResource(iconRes)
        tvMessage.text = message
        textOk?.let { btnOk.text = it }
        textNo?.let { btnNo.text = it }
    }

}