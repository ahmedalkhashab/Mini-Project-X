package io.android.projectx.androidextensions.security

@Throws(Exception::class)
fun encrypt(source: String): String {
    var encrypted = ""
    try {
        encrypted = AESUtils.encrypt(source)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return encrypted
}

fun decrypt(encrypted: String): String {
    var decrypted = ""
    try {
        decrypted = AESUtils.decrypt(encrypted)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return decrypted
}
