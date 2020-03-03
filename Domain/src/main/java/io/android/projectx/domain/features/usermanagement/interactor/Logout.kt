package io.android.projectx.domain.features.usermanagement.interactor

import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.base.interactor.CompletableUseCase
import io.android.projectx.domain.features.usermanagement.repository.UserManagementRepository
import io.reactivex.Completable
import javax.inject.Inject

open class Logout @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Logout.Params?>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return userManagementRepository.logout(params.email)
    }

    data class Params constructor(val email: String) {
        companion object {
            fun forLogout(email: String): Params {
                return Params(email)
            }
        }
    }

}