package io.android.projectx.domain.features.restaurants.interactor.browse

import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.base.interactor.ObservableUseCase
import io.android.projectx.domain.features.restaurants.repository.RestaurantsRepository
import io.android.projectx.domain.features.restaurants.model.Restaurant
import io.reactivex.Observable
import javax.inject.Inject

open class GetRestaurants @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Restaurant>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Restaurant>> {
        return restaurantsRepository.getRestaurants()
    }

}