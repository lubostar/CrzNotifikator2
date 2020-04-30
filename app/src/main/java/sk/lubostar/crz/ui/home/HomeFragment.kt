package sk.lubostar.crz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import sk.lubostar.crz.databinding.FragmentHomeBinding
import sk.lubostar.crz.ui.adapters.ContractsAdapter

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by lazy{
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel
        binding.recyclerView.adapter = ContractsAdapter(ContractsAdapter.OnClickListener{
            homeViewModel.displayContractDetails(it)
        })

        homeViewModel.text.observe(this, Observer {
            text_home.text = it
        })

//        homeViewModel.contracts.observe(this, Observer {
//            if(it.isNotEmpty()){
//                recyclerView.adapter = ContractsAdapter(it)
//            }
//        })

//        runBlocking {
//            homeViewModel.refreshContracts()
//        }

        return binding.root
    }
}