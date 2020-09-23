package jp.solucoes.checklistapplication.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import jp.solucoes.checklistapplication.R
import jp.solucoes.checklistapplication.model.Item
import jp.solucoes.checklistapplication.model.StatusList
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private val item: ArrayList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        with(rvList){
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = HomeListAdapter(item, viewModel, this@HomeActivity)
        }

        viewModel.listItem.observe(this, {
            item.clear()
            item.addAll(it)
            rvList?.adapter?.notifyDataSetChanged()
        })

        viewModel.counter.observe(this, {
            tabLayout.getTabAt(0)?.text = "ALL (${it.all})"
            tabLayout.getTabAt(1)?.text = "TO DO (${it.todo})"
            tabLayout.getTabAt(2)?.text = "DOING (${it.doing})"
            tabLayout.getTabAt(3)?.text = "DONE (${it.done})"
        })

        fabAdd.setOnClickListener { viewModel.addItem() }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> viewModel.getItem(StatusList.ALL)
                    1 -> viewModel.getItem(StatusList.TODO)
                    2 -> viewModel.getItem(StatusList.DOING)
                    3 -> viewModel.getItem(StatusList.DONE)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        viewModel.getItem(StatusList.ALL)
    }
}