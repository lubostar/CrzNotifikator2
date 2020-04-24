package sk.lubostar.crz.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.await
import sk.lubostar.crz.network.CrzApiService
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
            Contract(1, "001", "MinV", "Fero",
            "Prenajom vozidiel", "22. Apr 2020", 2.0, 200.0)
        )
    }
}