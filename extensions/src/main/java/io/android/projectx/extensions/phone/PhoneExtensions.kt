package io.android.projectx.extensions.phone

fun isValidSaudiMobile(mobile: String): Boolean {
    val pattern = "(\\u0660\\u0665[\\u0660-\\u0669]{8})|(05[\\d]{8})"
    return mobile.matches(pattern.toRegex())
}