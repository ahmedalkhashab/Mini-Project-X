package io.android.projectx.domain.features.usermanagement.interactor

import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.base.interactor.CompletableUseCase
import io.android.projectx.domain.features.usermanagement.repository.UserManagementRepository
import io.reactivex.Completable
import javax.inject.Inject

open class LogoutMobile @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Nothing?>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Nothing?): Completable {
        return userManagementRepository.logout()
    }

}