package sk.lubostar.crz

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import sk.lubostar.crz.network.model.Contract
import sk.lubostar.crz.ui.adapters.ContractsAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Contract>?){
    val adapter = recyclerView.adapter as ContractsAdapter
    adapter.submitList(data)
}