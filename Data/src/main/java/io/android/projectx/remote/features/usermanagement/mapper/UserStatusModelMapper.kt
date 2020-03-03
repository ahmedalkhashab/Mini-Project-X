package io.android.projectx.remote.features.usermanagement.mapper

import io.android.projectx.data.features.usermanagement.model.UserStatusEntity
import io.android.projectx.remote.base.mapper.ModelMapper
import io.android.projectx.remote.features.usermanagement.model.UserStatusModel
import javax.inject.Inject

open class UserStatusModelMapper @Inject constructor() :
    ModelMapper<UserStatusModel, UserStatusEntity> {

    override fun mapFromModel(model: UserStatusModel): UserStatusEntity {
        return when (model) {
            is UserStatusModel.Registered -> UserStatusEntity.Registered
            is UserStatusModel.Anonymous -> UserStatusEntity.Anonymous
        }
    }

}