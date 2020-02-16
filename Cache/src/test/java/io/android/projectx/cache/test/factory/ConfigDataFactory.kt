package io.android.projectx.cache.test.factory

import io.android.projectx.cache.features.recipes.model.Config
import io.android.projectx.cache.test.factory.DataFactory

object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(
            DataFactory.randomInt(),
            DataFactory.randomLong()
        )
    }

}