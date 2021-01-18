package io.android.projectx.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import io.android.projectx.androidextensions.getSupportColor
import kotlinx.android.synthetic.main.empty_view.view.*

class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    lateinit var view: View

    // Attributes
    @LayoutRes
    private var layoutRes = 0
    private var message = ""
    @ColorInt
    private var textColor = 0
    private var image = 0
    @ColorInt
    private var bgColor = 0
    //btnAction
    private var btnText: String? = null
    private var btnTextColor = 0
    private var btnBgColor = 0

    init {
        attrs?.let {
            extractAttributes(it)
            view = inflate(context, layoutRes, this)
            initData()
        }
    }

    private fun extractAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyView)
        layoutRes = typedArray.getResourceId(R.styleable.EmptyView_android_layout, R.layout.empty_view)
        message = typedArray.getString(R.styleable.EmptyView_android_text) ?: ""
        textColor = typedArray.getColor(
            R.styleable.EmptyView_android_textColor,
            context.getSupportColor(android.R.color.black)
        )
        image = typedArray.getResourceId(R.styleable.EmptyView_android_src, 0)
        bgColor = typedArray.getColor(
            R.styleable.EmptyView_android_background,
            context.getSupportColor(android.R.color.transparent)
        )
        btnText = typedArray.getString(R.styleable.EmptyView_buttonText)
        btnTextColor = typedArray.getColor(R.styleable.EmptyView_buttonTextColor, 0)
        btnBgColor = typedArray.getColor(R.styleable.EmptyView_buttonBgColor, 0)
        typedArray.recycle()
    }

    private fun initData() {
        view.message.text = message
        view.message.setTextColor(textColor)
        view.image.setImageResource(image)
        view.setBackgroundColor(bgColor)
        btnText?.let {
            view.btnAction.text = it
            view.btnAction.setTextColor(btnTextColor)
            view.btnAction.setBackgroundColor(btnBgColor)
        }
    }

    fun show(message: String = this.message, img: Int = image, onClick: (() -> Unit)? = null) {
        this.visibility = View.VISIBLE
        view.message.text = message
        view.image.setImageResource(img)
        if (btnText != null && onClick != null) {
            view.btnAction.setOnClickListener { onClick.invoke() }
            view.btnAction.visibility = View.VISIBLE
        }
        else view.btnAction.visibility = View.GONE
    }

    fun hide() {
        this.visibility = View.INVISIBLE
    }

}