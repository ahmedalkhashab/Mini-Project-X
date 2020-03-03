package io.android.projectx.data.features.usermanagement.mapper

import io.android.projectx.data.base.mapper.EntityMapper
import io.android.projectx.data.features.usermanagement.model.UserStatusEntity
import io.android.projectx.domain.features.usermanagement.model.UserStatus
import javax.inject.Inject

open class UserStatusMapper @Inject constructor() :
    EntityMapper<UserStatusEntity, UserStatus> {

    override fun mapFromEntity(entity: UserStatusEntity): UserStatus {
        return when (entity) {
            is UserStatusEntity.Registered -> UserStatus.Registered
            is UserStatusEntity.Anonymous -> UserStatus.Anonymous
        }
    }

    override fun mapToEntity(domain: UserStatus): UserStatusEntity {
        return when (domain) {
            is UserStatus.Registered -> UserStatusEntity.Registered
            is UserStatus.Anonymous -> UserStatusEntity.Anonymous
        }
    }

}