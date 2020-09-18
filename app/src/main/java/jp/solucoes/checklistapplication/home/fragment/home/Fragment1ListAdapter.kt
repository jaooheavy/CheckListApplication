package jp.solucoes.checklistapplication.home.fragment.home

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.solver.state.State
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import jp.solucoes.checklistapplication.R
import jp.solucoes.checklistapplication.model.ListHome
import jp.solucoes.checklistapplication.model.StatusList
import jp.solucoes.checklistapplication.utils.Metrics
import kotlinx.android.synthetic.main.list_home.view.*

class Fragment1ListAdapter(private val listHome: ArrayList<ListHome>, private val viewModel: Fragment1ViewModel, private val context: Context): RecyclerView.Adapter<Fragment1ListAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.list_home, parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (position == 0){
            val llm = holder.itemView.tvName.layoutParams as ConstraintLayout.LayoutParams
            llm.topMargin = Metrics.dipToPixels(context, 45f).toInt()
            holder.itemView.tvName.layoutParams = llm
        }

        holder.itemView.tvName.text = listHome[position].name
        when(listHome[position].status){
            StatusList.TODO->holder.itemView.tvName.setTextColor(Color.BLACK)
            StatusList.DOING->holder.itemView.tvName.setTextColor(Color.BLUE)
            StatusList.COMPLETED->holder.itemView.tvName.setTextColor(Color.RED)
        }


        holder.itemView.setOnClickListener {
            when(listHome[position].status){
                StatusList.TODO->viewModel.editItem(position, StatusList.DOING)
                StatusList.DOING->viewModel.editItem(position, StatusList.COMPLETED)
                StatusList.COMPLETED->viewModel.editItem(position, StatusList.TODO)
            }
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