package jp.solucoes.checklistapplication.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import jp.solucoes.checklistapplication.R
import jp.solucoes.checklistapplication.home.HomeActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        /*viewModel.responseIon.observe(this, { response ->

        })*/


        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        }, 3000)
    }

    override fun onBackPressed() {}
}