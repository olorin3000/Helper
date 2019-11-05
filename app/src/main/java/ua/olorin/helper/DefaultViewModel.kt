package ua.olorin.helper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.olorin.helper.data.Data
import ua.olorin.helper.data.Result
import ua.olorin.helper.repository.Repository

class DefaultViewModel(private val repository: Repository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean> get() = _empty

    private val _data = MutableLiveData<List<Data>>()
    val data: LiveData<List<Data>> get() = _data

    fun getServerData(path: String) = viewModelScope.launch{
        _loading.value = true

        val response = repository.getServerData(path)

        when(response.status){
            Result.Status.SUCCESS ->{
                response.data?.let {
                    if (it.data.isEmpty()) _empty.value = true
                    else _data.value = it.data
                }
            }

            Result.Status.ERROR ->{
                _error.value = response.message
            }
        }
        _loading.value = false
    }

}
