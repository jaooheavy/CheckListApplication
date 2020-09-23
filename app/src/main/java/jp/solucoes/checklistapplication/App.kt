package jp.solucoes.checklistapplication

import android.app.Application
import com.orhanobut.hawk.Hawk
import jp.solucoes.checklistapplication.repository.ListInterface
import jp.solucoes.checklistapplication.home.HomeViewModel
import jp.solucoes.checklistapplication.repository.ListDatabase
import jp.solucoes.checklistapplication.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
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
        viewModel { SplashViewModel() }
        viewModel { HomeViewModel(
            database = get ()
        ) }
        factory { ListDatabase() as ListInterface }
    }


}