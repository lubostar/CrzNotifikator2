package sk.lubostar.crz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import sk.lubostar.crz.R
import sk.lubostar.crz.databinding.FragmentHomeBinding
import sk.lubostar.crz.ui.adapters.ContractsAdapter
import sk.lubostar.crz.ui.decorators.SpacesItemDecorator

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by lazy{
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel
        binding.recyclerView.let {
            it.addItemDecoration(SpacesItemDecorator(resources
                .getDimensionPixelSize(R.dimen.list_item_margin)))
            it.adapter = ContractsAdapter(ContractsAdapter.OnClickListener{ contract ->
                homeViewModel.displayContractDetails(contract)
            })
        }

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            text_home.text = it
        })

        return binding.root
    }
}