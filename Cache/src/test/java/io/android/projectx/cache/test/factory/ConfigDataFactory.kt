package io.android.projectx.cache.test.factory

import io.android.projectx.cache.recipes.model.Config

object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }

}