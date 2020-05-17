package io.android.projectx.presentation.features.language

import android.os.Bundle
import android.view.View
import io.android.projectx.androidextensions.LocalizationHandler
import io.android.projectx.androidextensions.LocalizationHandler.LOCALE_ARABIC
import io.android.projectx.androidextensions.LocalizationHandler.LOCALE_ENGLISH
import io.android.projectx.androidextensions.initVerticalRecycler
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.BaseFragment
import io.android.projectx.presentation.base.model.LanguageView
import io.android.projectx.presentation.base.Adapter
import kotlinx.android.synthetic.main.language_adapter_item.view.*
import kotlinx.android.synthetic.main.language_fragment.recyclerView

class LanguageFragment : BaseFragment(R.layout.language_fragment) {

    private val viewModel: LanguageViewModel by appViewModels()
    private lateinit var adapter: Adapter<LanguageView>
    private lateinit var currentLanguageKey: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        currentLanguageKey = LocalizationHandler.getCurrentLocale(requireContext())
        initUi()
    }

    private fun initUi() {
        val data: List<LanguageView> = getAvailableLanguages()
        adapter = Adapter(
            R.layout.language_adapter_item,
            onClick = { _, item -> onClick(item) },
            onBind = { _, item, view -> onBind(item, view) }
        )
        adapter.items = data
        recyclerView.initVerticalRecycler(adapter)
    }

    private fun getAvailableLanguages(): List<LanguageView> {
        val data: MutableList<LanguageView> = mutableListOf()
        data.add(LanguageView(LOCALE_ENGLISH, getString(R.string.english)))
        data.add(LanguageView(LOCALE_ARABIC, getString(R.string.arabic)))
        return data
    }

    private fun <T> onClick(item: T) {
        val language = item as LanguageView
        if (language.key != currentLanguageKey) {
            LocalizationHandler.setNewLocale(requireContext(), language.key)
            requireActivity().recreate()
        }
    }

    private fun <T> onBind(item: T, view: View) {
        val language = item as LanguageView
        view.name.text = language.displayedText
        view.btnChecked.isChecked = language.key == currentLanguageKey
    }

}