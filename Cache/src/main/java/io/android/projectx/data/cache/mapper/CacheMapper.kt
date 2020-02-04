package io.android.projectx.data.cache.mapper

interface CacheMapper<C, E> {

    fun mapFromCached(type: C): E

    fun mapToCached(type: E): C

}