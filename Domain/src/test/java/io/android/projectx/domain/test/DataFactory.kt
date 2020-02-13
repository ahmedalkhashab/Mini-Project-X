package io.android.projectx.domain.test

import java.time.LocalDate
import java.util.*
import java.util.UUID
import java.util.concurrent.ThreadLocalRandom

object DataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun randomDate(): Date {
        val random = ThreadLocalRandom.current()
            .nextLong(LocalDate.of(2016, 1, 1).toEpochDay(), LocalDate.now().toEpochDay())
        return Date(random)
    }

    fun uniqueId(): Long {
        // https://stackoverflow.com/a/41613362/954752
        return UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE
    }

}