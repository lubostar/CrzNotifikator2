package sk.lubostar.crz.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sk.lubostar.crz.databinding.ListItemContractBinding
import sk.lubostar.crz.network.model.Contract

class ContractsAdapter(private val onClickListener: OnClickListener)
    :ListAdapter<Contract, ContractsAdapter.ContractViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContractViewHolder(
        ListItemContractBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ContractViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Contract>() {
        override fun areItemsTheSame(oldItem: Contract, newItem: Contract): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Contract, newItem: Contract): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ContractViewHolder(private val binding: ListItemContractBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(contract: Contract){
            binding.contract = contract
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (contract: Contract) -> Unit) {
        fun onClick(contract: Contract) = clickListener(contract)
    }
}