package io.android.projectx.remote.features.usermanagement.mapper

import io.android.projectx.data.features.usermanagement.model.UserEntity
import io.android.projectx.remote.base.mapper.ModelMapper
import io.android.projectx.remote.features.usermanagement.model.UserModel
import javax.inject.Inject

open class UserModelMapper @Inject constructor(private val mapperUserModelStatus: UserStatusModelMapper) :
    ModelMapper<UserModel, UserEntity> {

    override fun mapFromModel(model: UserModel): UserEntity {
        return UserEntity(
            model.id,
            model.name,
            model.email,
            model.mobile,
            mapperUserModelStatus.mapFromModel(model.userStatus)
        )
    }

}