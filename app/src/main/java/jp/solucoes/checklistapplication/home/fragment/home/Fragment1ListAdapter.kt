package jp.solucoes.checklistapplication.home.fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.solucoes.checklistapplication.R
import kotlinx.android.synthetic.main.list_home.view.*

class Fragment1ListAdapter(private val listHome: List<String>, private val viewModel: Fragment1ViewModel): RecyclerView.Adapter<Fragment1ListAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.list_home, parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.tvName.text = listHome[position]

        holder.itemView.setOnClickListener {
            viewModel.editItem(position)
        }

        holder.itemView.setOnLongClickListener {
            viewModel.deleteItem(position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return listHome.size
    }

    class VH(itemView: View): RecyclerView.ViewHolder(itemView)
}