package jp.solucoes.checklistapplication.home.fragment.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.solucoes.checklistapplication.R
import jp.solucoes.checklistapplication.model.ListHome
import jp.solucoes.checklistapplication.model.StatusList
import jp.solucoes.checklistapplication.utils.Metrics
import kotlinx.android.synthetic.main.list_home.view.*

class FragmentHomeListAdapter(private val listHome: ArrayList<ListHome>, private val viewModel: FragmentHomeViewModel, private val context: Context): RecyclerView.Adapter<FragmentHomeListAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.list_home, parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (position >= (itemCount-1)){
            val llm = holder.itemView.container.layoutParams as RecyclerView.LayoutParams
            llm.bottomMargin = Metrics.dipToPixels(context, 80f).toInt()
            holder.itemView.container.layoutParams = llm
        }else{
            val llm = holder.itemView.container.layoutParams as RecyclerView.LayoutParams
            val m = Metrics.dipToPixels(context, 8f).toInt()
            llm.setMargins(m,m,m,m)
            holder.itemView.container.layoutParams = llm
        }

        //holder.itemView.tvName.text = listHome[position].name
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