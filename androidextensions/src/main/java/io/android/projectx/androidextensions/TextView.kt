package io.android.projectx.androidextensions

import android.text.Html
import android.widget.TextView
import android.content.Context
import android.graphics.Typeface
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatTextView

fun AppCompatTextView.applyDrawableEnd(attrId: Int) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, context.getAttrValueResId(attrId), 0)
}

fun AppCompatTextView.applyDrawableStartAttr(attrId: Int) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(context.getAttrValueResId(attrId), 0, 0, 0)
}

fun AppCompatTextView.applyDrawableStart(resId: Int) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(resId, 0, 0, 0)
}

fun AppCompatTextView.applyTextColorBackground(attrColor: Int, @DrawableRes resId: Int? = null) {
    setTextColor(context.getThemeColor(attrColor))
    background = resId?.let { context.getSupportDrawable(it) } ?: run { null }
}

fun TextView?.setFont(context: Context?, fontName: String) {
    this?.typeface = Typeface.createFromAsset(context?.assets, fontName)
}

fun TextView.setHtml(content: String) {
    text = Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT)
}