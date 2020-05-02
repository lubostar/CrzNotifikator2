package sk.lubostar.crz.ui.home

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import sk.lubostar.crz.network.CrzApiService
import sk.lubostar.crz.network.CrzApiStatus
import sk.lubostar.crz.network.model.Contract

class HomeViewModel : ViewModel() {
    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<CrzApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<CrzApiStatus>
        get() = _status

    private val _contracts = MutableLiveData<List<Contract>>()
    val contracts: LiveData<List<Contract>> get() = _contracts

    val emptyTextVisibility = Transformations.map(contracts){
        if(status.value == CrzApiStatus.DONE && it.isNullOrEmpty()) VISIBLE else GONE
    }

    init {
        getContracts()
    }

    private fun getContracts(){
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            val getContractsDeferred = CrzApiService.CrzApi.retrofitService.getContracts()
            try {
                _status.value = CrzApiStatus.LOADING
                // this will run on a thread managed by Retrofit
                val listResult = getContractsDeferred.await()
                _status.value = CrzApiStatus.DONE
                _contracts.value = listResult
            } catch (e: Exception) {
                _status.value = CrzApiStatus.ERROR
                _contracts.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayContractDetails(contract: Contract) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}