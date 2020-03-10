package io.android.projectx.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText
import io.android.projectx.androidextensions.getSupportColor
import timber.log.Timber

class TextInputLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.Widget_MaterialComponents_TextInputLayout_FilledBox_Dense
) : TextInputLayout(context, attrs, defStyleAttr) {

    // EditText
    @ColorInt
    private var textColor = 0
    private var textSize = 0f
    private var fontFamily = 0
    private var imeOptions = EditorInfo.IME_ACTION_NEXT
    private var maxLines = 0
    private var lines = 0
    private var inputType = EditorInfo.TYPE_TEXT_VARIATION_NORMAL
    private var selectAllOnFocus = true

    init {
        extractAttributes(attrs)
        addEditText()
        //setWillNotDraw(false)
    }

    private fun extractAttributes(attrs: AttributeSet?) {
        attrs ?: return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextInputLayout)
        val n: Int = typedArray.indexCount
        for (i in 0 until n) {
            when (val attr: Int = typedArray.getIndex(i)) {
                R.styleable.TextInputLayout_android_textColor ->
                    textColor =
                        typedArray.getColor(attr, context.getSupportColor(android.R.color.black))
                R.styleable.TextInputLayout_android_textSize ->
                    textSize = typedArray.getDimension(attr, 16f)
                R.styleable.TextInputLayout_android_fontFamily ->
                    fontFamily = typedArray.getResourceId(attr, 0)
                R.styleable.TextInputLayout_android_imeOptions ->
                    imeOptions = typedArray.getInt(attr, EditorInfo.IME_ACTION_NEXT)
                R.styleable.TextInputLayout_android_maxLines ->
                    maxLines = typedArray.getInt(attr, 1)
                R.styleable.TextInputLayout_android_lines ->
                    lines = typedArray.getInt(attr, 1)
                R.styleable.TextInputLayout_android_inputType ->
                    inputType = typedArray.getInt(attr, EditorInfo.TYPE_TEXT_VARIATION_NORMAL)
                R.styleable.TextInputLayout_android_selectAllOnFocus ->
                    selectAllOnFocus = typedArray.getBoolean(attr, true)
                else -> Timber.d("Unknown attribute for $javaClass: $attr")
            }
        }
        typedArray.recycle()
    }

    private fun addEditText() {
        // Instantiate EditText view which will be held inside of TextInputLayout
        val editText = TextInputEditText(
            context,
            null,
            R.style.Widget_MaterialComponents_TextInputEditText_FilledBox_Dense
        )
        editText.id = View.generateViewId()
        editText.setTextColor(textColor)
        editText.textSize = textSize
        editText.typeface = ResourcesCompat.getFont(context, fontFamily)
        editText.imeOptions = imeOptions
        editText.maxLines = maxLines
        editText.setLines(lines)
        editText.inputType = inputType
        editText.setSelectAllOnFocus(selectAllOnFocus)
        // Define layout params for the EditTExt field
        val editTextParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        // Set editText layout parameters to the editText field
        editText.layoutParams = editTextParams
        // Then you add editText into a textInputLayout
        addView(editText, editTextParams)
        // Lastly, you add the textInputLayout into (or onto) the layout you've chosen.
    }

}