package io.android.projectx.widget

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class WatchedEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    lateinit var mListeners: MutableList<TextWatcher>

    override fun addTextChangedListener(watcher: TextWatcher) {
        if (!this::mListeners.isInitialized) mListeners = mutableListOf()
        mListeners.add(watcher)
        super.addTextChangedListener(watcher)
    }

    fun removeAllTextWatchers() {
        if (this::mListeners.isInitialized) {
            mListeners.forEach {
                mListeners.remove(it)
                super.removeTextChangedListener(it)
            }
        }
    }

    override fun removeTextChangedListener(watcher: TextWatcher) {
        if (this::mListeners.isInitialized) {
            val i = mListeners.indexOf(watcher)
            if (i >= 0) {
                mListeners.removeAt(i)
                super.removeTextChangedListener(watcher)
            }
        }
    }

}