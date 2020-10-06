package io.android.projectx.extensions

import java.text.DecimalFormat
import kotlin.random.Random.Default.nextInt

fun Int.random(): Int = nextInt(0, 10000 + 1)

fun Int.random(from: Int, until: Int): Int = nextInt(from, until)

fun Double.toPrice(): String {
    val decimalFormat = DecimalFormat("#,###.00")
    decimalFormat.groupingSize = 3
    return decimalFormat.format(this)
}

fun Float.toPrice(): String {
    val decimalFormat = DecimalFormat("#,###.00")
    decimalFormat.groupingSize = 3
    return decimalFormat.format(this)
}