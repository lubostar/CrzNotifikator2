package sk.lubostar.crz.ui.home

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sk.lubostar.crz.network.model.Contract

class HomeViewModel : ViewModel() {
    val contracts: LiveData<List<Contract>> get() = _contracts
    private val _contracts = MutableLiveData<List<Contract>>(getFakeData())

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text

//    suspend fun refreshContracts(){
//        val result = CrzApiService.CrzApi.retrofitService.getContracts().await()
//        _contracts.value = result
//        _text.value = "There is " + result.size + " contracts"
//    }

    fun getFakeData() :List<Contract>{
        return listOf(
            Contract(1, "001", "Ministerstvo Vnutra", "Dodavatel",
                "Prenajom vozidiel", "22. Apr\n2020", 2.0, 200.0),
            Contract(2, "001", "Ministerstvo Vnutra", "Dodavatel",
                "Prenajom vozidiel 2", "22. Apr\n2020", 2.0, 200.0),
            Contract(3, "001", "Ministerstvo Vnutra", "Dodavatel",
                "Prenajom vozidiel 3", "22. Apr\n2020", 2.0, 200.0),
            Contract(4, "001", "Ministerstvo Vnutra", "Dodavatel",
                "Prenajom vozidiel 4", "22. Apr\n2020", 2.0, 200.0)
        )
    }

    fun getEmptyTextVisibility() = if(contracts.value.isNullOrEmpty()) VISIBLE else GONE

    fun displayContractDetails(contract: Contract) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}