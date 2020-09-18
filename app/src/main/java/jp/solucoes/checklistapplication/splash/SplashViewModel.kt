package jp.solucoes.checklistapplication.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import jp.solucoes.checklistapplication.model.IonResponse
import kotlinx.coroutines.*

class SplashViewModel: ViewModel() {
    val responseIon: MutableLiveData<IonResponse> = MutableLiveData()

    fun startTimerSplash(){
        CoroutineScope(Dispatchers.Default).launch {
            delay(3000)
            responseIon.value = IonResponse.success(null as JsonObject)
        }
    }

}