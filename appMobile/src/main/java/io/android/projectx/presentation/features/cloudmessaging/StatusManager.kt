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

    fun updateStatus(resource: Resource<CaseStatus?>, forceUpdate: Boolean = false) {
        if (forceUpdate) _status.postValue(resource)
        else if (resource.data != status.value?.data) _status.postValue(resource)
    }

}