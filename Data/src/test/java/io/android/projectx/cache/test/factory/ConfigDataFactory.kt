package io.android.projectx.cache.test.factory

import io.android.projectx.cache.features.config.model.Config

object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomLong()
        )
    }

}