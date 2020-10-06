package io.android.projectx.androidextensions

import android.content.res.ColorStateList
import androidx.annotation.ColorInt

fun colorStateListOf(@ColorInt color: Int): ColorStateList = ColorStateList.valueOf(color)