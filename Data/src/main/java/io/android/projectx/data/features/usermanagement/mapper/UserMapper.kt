package io.android.projectx.data.features.usermanagement.mapper

import io.android.projectx.data.base.mapper.EntityMapper
import io.android.projectx.data.features.usermanagement.model.UserEntity
import io.android.projectx.domain.features.usermanagement.model.User
import javax.inject.Inject

open class UserMapper @Inject constructor(private val mapperUserStatus: UserStatusMapper) :
    EntityMapper<UserEntity, User> {

    override fun mapFromEntity(entity: UserEntity): User {
        return User(
            entity.id,
            entity.name,
            entity.email,
            entity.mobile,
            mapperUserStatus.mapFromEntity(entity.userStatus)
        )
    }

    override fun mapToEntity(domain: User): UserEntity {
        return UserEntity(
            domain.id,
            domain.name,
            domain.email,
            domain.mobile,
            mapperUserStatus.mapToEntity(domain.userStatus)
        )
    }

}