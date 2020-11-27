package sk.lubostar.crz.ui.home

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import sk.lubostar.crz.network.CrzApiService
import sk.lubostar.crz.network.CrzApiStatus
import sk.lubostar.crz.network.model.Contract
import kotlin.collections.ArrayList

class HomeViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<CrzApiStatus>()

    private var disposable: Disposable? = null

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

    private fun onResponse(response: List<Contract>) {
        _status.value = CrzApiStatus.DONE
        _contracts.value = response
    }

    private fun onError(error: Throwable) {
        _status.value = CrzApiStatus.ERROR
        _contracts.value = ArrayList()
        error.printStackTrace()
    }

    private fun getContracts(){
        val source = CrzApiService.CrzApi.retrofitService.getContracts()
        disposable = source
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _status.value = CrzApiStatus.LOADING }
            .subscribe(
                {response -> onResponse(response)},
                {error -> onError(error)})
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun displayContractDetails(contract: Contract) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}