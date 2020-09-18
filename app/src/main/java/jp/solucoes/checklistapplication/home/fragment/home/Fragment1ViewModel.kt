package jp.solucoes.checklistapplication.home.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment1ViewModel() : ViewModel(){
    val listHome: MutableLiveData<ArrayList<String>> = MutableLiveData()
    fun getAllList(){
        CoroutineScope(Dispatchers.Main).launch {
            val list = withContext(Dispatchers.Default) { Fragment1Database.getDatabaseListHome() }
            listHome.value = list
        }
    }

    fun addOneMore(){
        CoroutineScope(Dispatchers.Default).launch {
            val list = listHome.value!!
            list.add("Add")
            Fragment1Database.saveDatabaseListHome(list)
            withContext(Dispatchers.Main){ listHome.value = list }
        }
    }

    fun deleteItem(position: Int){
        CoroutineScope(Dispatchers.Default).launch {
            val list = listHome.value!!
            list.removeAt(position)
            Fragment1Database.saveDatabaseListHome(list)
            withContext(Dispatchers.Main){ listHome.value = list }
        }
    }

    fun editItem(position: Int){
        CoroutineScope(Dispatchers.Default).launch {
            val list = listHome.value!!
            list[position] = "Edited"
            Fragment1Database.saveDatabaseListHome(list)
            withContext(Dispatchers.Main){ listHome.value = list }
        }
    }
}