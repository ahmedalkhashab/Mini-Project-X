package io.android.projectx.presentation.base

import io.android.projectx.presentation.base.state.Resource

interface DialogListener {
    fun <T> onDataReceived(result: Resource<T>)
}