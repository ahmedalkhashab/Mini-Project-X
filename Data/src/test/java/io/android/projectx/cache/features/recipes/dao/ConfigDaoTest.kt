package io.android.projectx.cache.features.recipes.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import io.android.projectx.cache.AppDatabase
import io.android.projectx.cache.test.factory.ConfigDataFactory
import io.android.projectx.cache.test.factory.DataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ConfigDaoTest {

    @Rule
    @JvmField var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        AppDatabase::class.java)
        .allowMainThreadQueries()
        .build()

    @After
    fun clearDb() {
        database.close()
    }

    @Test
    fun saveConfigurationSavesData() {
        val config = ConfigDataFactory.makeCachedConfig()
        database.configDao().insertConfig(config)
        val key = DataFactory.randomString()
        val testObserver = database.configDao().getConfig(key).test()
        testObserver.assertValue(config)
    }

}