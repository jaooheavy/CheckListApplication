package jp.solucoes.checklistapplication.home.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import jp.solucoes.checklistapplication.R
import jp.solucoes.checklistapplication.model.ListHome
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentHome: Fragment() {
    private val viewModel: FragmentHomeViewModel by viewModel()
    private val listHome: ArrayList<ListHome> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rvList){
            layoutManager = LinearLayoutManager(this@FragmentHome.context)
            adapter = this@FragmentHome.context?.let { FragmentHomeListAdapter(listHome, viewModel, it) }
        }

        viewModel.listHome.observe(viewLifecycleOwner, {
            listHome.clear()
            listHome.addAll(it)
            rvList?.adapter?.notifyDataSetChanged()
        })

        viewModel.counterHome.observe(viewLifecycleOwner, {
            tvCounter1.text = it.todo.toString()
            tvCounter2.text = it.doing.toString()
            tvCounter3.text = it.complete.toString()
        })

        fabAdd.setOnClickListener { viewModel.addOneMore() }

        viewModel.getAllList()
    }
}