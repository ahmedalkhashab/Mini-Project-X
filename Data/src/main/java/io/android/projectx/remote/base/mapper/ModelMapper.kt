package io.android.projectx.remote.base.mapper

interface ModelMapper<in M, out E> {

    fun mapFromModel(model: M): E

}