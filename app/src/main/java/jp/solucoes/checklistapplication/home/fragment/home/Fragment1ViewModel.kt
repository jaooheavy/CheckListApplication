package jp.solucoes.checklistapplication.home.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.solucoes.checklistapplication.model.CounterHome
import jp.solucoes.checklistapplication.model.ListHome
import jp.solucoes.checklistapplication.model.StatusList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment1ViewModel() : ViewModel(){
    val listHome: MutableLiveData<ArrayList<ListHome>> = MutableLiveData()
    val counterHome: MutableLiveData<CounterHome> = MutableLiveData()

    fun getAllList(){
        CoroutineScope(Dispatchers.Main).launch {
            val list = withContext(Dispatchers.Default) { Fragment1Database.getDatabaseListHome() }
            val counter = withContext(Dispatchers.Default){ counterList(list) }
            counterHome.value = counter
            listHome.value = list
        }
    }

    private suspend fun counterList(list: ArrayList<ListHome>): CounterHome{
        var todo = 0
        var doing = 0
        var complete = 0

        for (i in list){
            when(i.status){
                StatusList.TODO->todo++
                StatusList.DOING->doing++
                StatusList.COMPLETED->complete++
            }
        }
        return CounterHome(todo, doing, complete)
    }

    fun addOneMore(){
        CoroutineScope(Dispatchers.Default).launch {
            val list = listHome.value!!
            list.add(ListHome(0, StatusList.TODO, "ADD", ArrayList()))
            Fragment1Database.saveDatabaseListHome(list)

            val counter = counterList(list)

            withContext(Dispatchers.Main){
                listHome.value = list
                counterHome.value = counter
            }
        }
    }

    fun deleteItem(position: Int){
        CoroutineScope(Dispatchers.Default).launch {
            val list = listHome.value!!
            list.removeAt(position)
            Fragment1Database.saveDatabaseListHome(list)

            val counter = counterList(list)

            withContext(Dispatchers.Main){
                listHome.value = list
                counterHome.value = counter
            }
        }
    }

    fun editItem(position: Int, value: StatusList){
        CoroutineScope(Dispatchers.Default).launch {
            val list = listHome.value!!
            list[position].status = value
            Fragment1Database.saveDatabaseListHome(list)

            val counter = counterList(list)

            withContext(Dispatchers.Main){
                listHome.value = list
                counterHome.value = counter
            }
        }
    }
}