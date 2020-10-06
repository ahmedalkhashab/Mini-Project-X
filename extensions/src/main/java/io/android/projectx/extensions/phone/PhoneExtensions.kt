package io.android.projectx.extensions.phone

fun isValidSaudiMobile(mobile: String, startingWithZero: Boolean = true): Boolean {
    val pattern = if (startingWithZero) "(\\u0660\\u0665[\\u0660-\\u0669]{8})|(05[\\d]{8})"
    else "(\\u0665[\\u0660-\\u0669]{8})|(5[\\d]{8})"
    return mobile.matches(pattern.toRegex())
}