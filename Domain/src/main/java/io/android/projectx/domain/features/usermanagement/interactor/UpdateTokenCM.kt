package io.android.projectx.domain.features.usermanagement.interactor

import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.base.interactor.SingleUseCase
import io.android.projectx.domain.features.usermanagement.repository.UserManagementRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Update the token of the device for the cloud messaging
 */
open class UpdateTokenCM @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Boolean, UpdateTokenCM.Params?>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Params?): Single<Boolean> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return userManagementRepository.updateDeviceToken(params.token)
    }

    data class Params constructor(val token: String) {
        companion object {
            fun forUpdateTokenCM(token: String): Params {
                return Params(token)
            }
        }
    }

}