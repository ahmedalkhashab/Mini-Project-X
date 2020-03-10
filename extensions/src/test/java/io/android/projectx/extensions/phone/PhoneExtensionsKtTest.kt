package io.android.projectx.extensions.phone

import org.junit.Test

import org.junit.Assert.*

class PhoneExtensionsKtTest {

    @Test
    fun isValidSaudiMobileStartingWith05() {
        val mobile = "0542113528"
        val isValid = isValidSaudiMobile(mobile)
        assertEquals(isValid,true)
    }

    @Test
    fun isValidSaudiMobileNotStartingWith05() {
        val mobile = "1142113528"
        val isValid = isValidSaudiMobile(mobile)
        assertEquals(isValid,false)
    }

    @Test
    fun isValidSaudiMobileStartingCountryCode() {
        val mobile = "+966542113528"
        val isValid = isValidSaudiMobile(mobile)
        assertEquals(isValid,false)
    }

}