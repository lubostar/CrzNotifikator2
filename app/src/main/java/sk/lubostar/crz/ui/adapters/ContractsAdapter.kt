package sk.lubostar.crz.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_contract.view.*
import sk.lubostar.crz.R
import sk.lubostar.crz.network.model.Contract

class ContractsAdapter(private val items :List<Contract>) :RecyclerView.Adapter<ContractViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_contract, parent, false)
        return ContractViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ContractViewHolder, position: Int) {
        items[position].let {
            holder.tvSubject.text = it.subject
            holder.tvDate.text = it.signed_on
        }
    }
}

class ContractViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val tvSubject :TextView = itemView.card_subject
    val tvDate :TextView = itemView.card_date
}