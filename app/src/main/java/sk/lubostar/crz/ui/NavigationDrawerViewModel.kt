package sk.lubostar.crz.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class NavigationDrawerViewModel: ViewModel() {
    private val _hasFilters = MutableLiveData<Boolean>()
    val hasFilters: LiveData<Boolean> get() = _hasFilters

    val hasFiltersVisibility = Transformations.map(hasFilters){
        if (it){
            View.VISIBLE
        }else{
            View.GONE
        }
    }

    init {
        _hasFilters.value = false // TEST
    }
}