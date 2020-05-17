package io.android.projectx.presentation.features.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.android.projectx.domain.features.restaurants.interactor.browse.GetRestaurants
import io.android.projectx.domain.features.restaurants.model.Restaurant
import io.android.projectx.presentation.base.mapper.RestaurantViewMapper
import io.android.projectx.presentation.base.model.RestaurantView
import io.android.projectx.presentation.base.state.Resource
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class RestaurantsViewModel @Inject constructor(
    private val getRestaurants: GetRestaurants?,
    private val mapper: RestaurantViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<RestaurantView>?>> = MutableLiveData()

    override fun onCleared() {
        getRestaurants?.dispose()
        super.onCleared()
    }

    fun getRestaurants(): LiveData<Resource<List<RestaurantView>?>> {
        return liveData
    }

    fun fetchRestaurants() {
        liveData.postValue(Resource.loading())
        getRestaurants?.execute(RestaurantsSubscriber())
    }

    inner class RestaurantsSubscriber : DisposableObserver<List<Restaurant>>() {

        override fun onNext(t: List<Restaurant>) = liveData.postValue(Resource.success(t.map { mapper.mapToView(it) }))

        override fun onComplete() {}

        override fun onError(e: Throwable) = liveData.postValue(Resource.error(ex = e))

    }

}