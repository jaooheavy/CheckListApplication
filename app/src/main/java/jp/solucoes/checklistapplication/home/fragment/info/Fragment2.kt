package jp.solucoes.checklistapplication.home.fragment.info

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jp.solucoes.checklistapplication.R
import jp.solucoes.checklistapplication.home.fragment.home.Fragment1ViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.ext.android.inject

class Fragment2: Fragment() {

    private val viewModel: Fragment2ViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        content?.setBackgroundColor(Color.GREEN)
    }
}