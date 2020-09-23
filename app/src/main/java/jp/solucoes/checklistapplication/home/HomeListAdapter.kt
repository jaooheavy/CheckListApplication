package jp.solucoes.checklistapplication.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import jp.solucoes.checklistapplication.R
import jp.solucoes.checklistapplication.model.Item
import jp.solucoes.checklistapplication.model.StatusList
import jp.solucoes.checklistapplication.utils.Metrics
import kotlinx.android.synthetic.main.list_home.view.*


class HomeListAdapter(
    private val item: ArrayList<Item>,
    private val viewModel: HomeViewModel,
    private val context: Context
): RecyclerView.Adapter<HomeListAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.list_home, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (position >= (itemCount-1)){
            val llm = holder.itemView.container.layoutParams as RecyclerView.LayoutParams
            llm.bottomMargin = Metrics.dipToPixels(context, 80f).toInt()
            holder.itemView.container.layoutParams = llm
        }else{
            val llm = holder.itemView.container.layoutParams as RecyclerView.LayoutParams
            val m = Metrics.dipToPixels(context, 8f).toInt()
            llm.setMargins(m, m, m, m)
            holder.itemView.container.layoutParams = llm
        }

        //holder.itemView.tvName.text = listHome[position].name
        when(item[position].status){
            StatusList.TODO -> holder.itemView.view.setBackgroundColor(Color.BLACK)
            StatusList.DOING -> holder.itemView.view.setBackgroundColor(Color.BLUE)
            StatusList.DONE -> holder.itemView.view.setBackgroundColor(Color.RED)
        }

        holder.itemView.setOnClickListener {
            when(item[position].status){
                StatusList.TODO -> viewModel.editItem(position, StatusList.DOING)
                StatusList.DOING -> viewModel.editItem(position, StatusList.DONE)
                StatusList.DONE -> viewModel.editItem(position, StatusList.TODO)
            }
        }
        holder.itemView.ivSettings.setOnClickListener {
            val popup = PopupMenu(context, it)
            popup.menuInflater
                .inflate(R.menu.popup_menu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId == R.id.delete)
                    viewModel.deleteItem(position)
                true
            }
            popup.show()
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    class VH(itemView: View): RecyclerView.ViewHolder(itemView)
}