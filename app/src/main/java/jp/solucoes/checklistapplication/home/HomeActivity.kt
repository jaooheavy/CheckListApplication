package jp.solucoes.checklistapplication.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import jp.solucoes.checklistapplication.R
import jp.solucoes.checklistapplication.home.fragment.home.Fragment1
import jp.solucoes.checklistapplication.home.fragment.Fragment2
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private val fragment1: Fragment1 by lazy { Fragment1() }
    private val fragment2: Fragment2 by lazy { Fragment2() }
    private val fragmentManager: FragmentManager by lazy {
        supportFragmentManager
    }
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        activeFragment = fragment1

        fragmentManager.beginTransaction().add(R.id.mainContent, fragment2, "2").hide(fragment2).commit()
        fragmentManager.beginTransaction().add(R.id.mainContent, fragment1, "1").commit()

        bottom.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.home ->{
                    fragmentManager.beginTransaction().hide(activeFragment).show(fragment1).commit()
                    activeFragment = fragment1
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.info ->{
                    fragmentManager.beginTransaction().hide(activeFragment).show(fragment2).commit()
                    activeFragment = fragment2
                    return@setOnNavigationItemSelectedListener true
                }
                else ->{
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }

    }
}