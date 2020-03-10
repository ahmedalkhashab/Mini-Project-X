package io.android.projectx.androidextensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun RecyclerView.initVerticalRecycler(adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    this.setHasFixedSize(true)
    this.adapter = adapter
}

fun RecyclerView.initHorizontalRecycler(adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    this.setHasFixedSize(true)
    this.adapter = adapter
}

fun RecyclerView.addVerticalItemDecoration(@DrawableRes resId: Int) {
    val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    itemDecoration.setDrawable(context.getSupportDrawable(resId)!!)
    addItemDecoration(itemDecoration)
}

fun RecyclerView.initGridRecycler(adapter: RecyclerView.Adapter<*>, spanCount: Int) {
    this.layoutManager = GridLayoutManager(context, spanCount)
    this.setHasFixedSize(true)
    this.adapter = adapter
}
