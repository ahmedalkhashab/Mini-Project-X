package io.android.projectx.presentation.test.factory

import java.time.LocalDate
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object DataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun randomLong(): Long {
        return randomInt().toLong()
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