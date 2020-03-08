package io.android.projectx.domain.features.usermanagement.interactor

import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.base.interactor.ObservableUseCase
import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.domain.features.usermanagement.repository.UserManagementRepository
import io.reactivex.Observable
import javax.inject.Inject

open class FetchUser @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<User, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<User> {
        return userManagementRepository.fetchUser()
    }

}