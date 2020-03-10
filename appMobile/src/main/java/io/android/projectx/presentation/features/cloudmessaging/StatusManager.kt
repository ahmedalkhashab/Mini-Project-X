package io.android.projectx.presentation.features.cloudmessaging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.android.projectx.presentation.base.state.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StatusManager @Inject constructor() {

    private val _status: MediatorLiveData<Resource<CaseStatus?>> = MediatorLiveData()
    val status: LiveData<Resource<CaseStatus?>> = _status

    fun updateStatus(data: Resource<CaseStatus?>){
        _status.postValue(data)
    }

}