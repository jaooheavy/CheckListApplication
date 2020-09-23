package jp.solucoes.checklistapplication.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import jp.solucoes.checklistapplication.R
import jp.solucoes.checklistapplication.home.fragment.home.FragmentHome
import jp.solucoes.checklistapplication.home.fragment.info.FragmentInfo
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private val fragmentHome: FragmentHome by lazy { FragmentHome() }
    private val fragmentInfo: FragmentInfo by lazy { FragmentInfo() }
    private val fragmentManager: FragmentManager by lazy {
        supportFragmentManager
    }
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        activeFragment = fragmentHome

        fragmentManager.beginTransaction().add(R.id.mainContent, fragmentInfo, "2").hide(fragmentInfo).commit()
        fragmentManager.beginTransaction().add(R.id.mainContent, fragmentHome, "1").commit()

        bottom.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.home ->{
                    fragmentManager.beginTransaction().hide(activeFragment).show(fragmentHome).commit()
                    activeFragment = fragmentHome
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.info ->{
                    fragmentManager.beginTransaction().hide(activeFragment).show(fragmentInfo).commit()
                    activeFragment = fragmentInfo
                    return@setOnNavigationItemSelectedListener true
                }
                else ->{
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }

    }
}