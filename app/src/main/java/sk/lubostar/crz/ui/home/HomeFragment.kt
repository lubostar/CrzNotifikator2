package sk.lubostar.crz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.runBlocking
import sk.lubostar.crz.R
import sk.lubostar.crz.ui.adapters.ContractsAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(this, Observer {
            text_home.text = it
        })

        homeViewModel.contracts.observe(this, Observer {
            if(it.isNotEmpty()){
                recyclerView.adapter = ContractsAdapter(it)
            }
        })

//        runBlocking {
//            homeViewModel.refreshContracts()
//        }

        return root
    }
}