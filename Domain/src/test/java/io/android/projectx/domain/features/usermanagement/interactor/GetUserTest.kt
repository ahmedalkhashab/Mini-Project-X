package io.android.projectx.domain.features.usermanagement.interactor

import com.nhaarman.mockitokotlin2.whenever
import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.domain.features.usermanagement.repository.UserManagementRepository
import io.android.projectx.domain.features.usermanagement.test.UserDataFactory
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetUserTest {
    private lateinit var getUser: GetUser
    @Mock
    lateinit var userManagementRepository: UserManagementRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getUser = GetUser(userManagementRepository, postExecutionThread)
    }

    @Test
    fun getUserCompletes() {
        stubGetUser(Observable.just(UserDataFactory.makeUser()))
        val testObserver = getUser.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getUserReturnsData() {
        val user = UserDataFactory.makeUser()
        stubGetUser(Observable.just(user))
        val testObserver = getUser.buildUseCaseObservable().test()
        testObserver.assertValue(user)
    }

    private fun stubGetUser(observable: Observable<User>) {
        whenever(userManagementRepository.getUser())
            .thenReturn(observable)
    }

}