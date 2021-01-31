package io.android.projectx.androidextensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(blockFun: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            blockFun.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.onTextChanged(blockFun: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            blockFun.invoke(s.toString())
        }
    })
}

fun EditText.setSearchBehaviour(
    searchAction: (text: String) -> Unit,
    emptyAction: ((length: Int) -> Unit)? = null,
    cancelAction: (() -> Unit)? = null
) = afterTextChanged { text ->
    SearchHelper.searchText(text, object : SearchHelper.SearchAction {
        override fun searchStarted(text: String) {
            searchAction.invoke(text)
        }

        override fun searchEmpty(length: Int) {
            emptyAction?.invoke(length)
        }

        override fun searchCanceled() {
            cancelAction?.invoke()
        }
    })
}


object SearchHelper {

    interface SearchAction {
        fun searchStarted(text: String)
        fun searchEmpty(length: Int)
        fun searchCanceled()
    }

    fun searchText(text: String, searchAction: SearchAction) {
        when {
            text.isNotEmpty() -> searchAction.searchStarted(text)
            text.isEmpty() -> searchAction.searchCanceled()
            else -> searchAction.searchEmpty(text.length)
        }
    }

}