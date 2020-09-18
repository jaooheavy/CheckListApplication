package jp.solucoes.checklistapplication

import android.app.Application
import com.orhanobut.hawk.Hawk
import jp.solucoes.checklistapplication.home.fragment.home.Fragment1ViewModel
import jp.solucoes.checklistapplication.home.fragment.info.Fragment2ViewModel
import jp.solucoes.checklistapplication.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        startKoin {
            androidContext(this@App)
            modules(moduleApp)
        }
    }



    private val moduleApp = module{
        factory { SplashViewModel() }
        factory { Fragment1ViewModel() }
        factory { Fragment2ViewModel() }
    }


}