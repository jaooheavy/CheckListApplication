package jp.solucoes.checklistapplication.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.solucoes.checklistapplication.model.Counter
import jp.solucoes.checklistapplication.model.Item
import jp.solucoes.checklistapplication.model.StatusList
import jp.solucoes.checklistapplication.repository.ListInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val database: ListInterface) : ViewModel(){
    private val listAllHome = ArrayList<Item>()
    val listItem: MutableLiveData<ArrayList<Item>> = MutableLiveData()
    val counter: MutableLiveData<Counter> = MutableLiveData()
    var currentList: StatusList = StatusList.ALL

    private suspend fun counterList(list: ArrayList<Item>): Counter{
        var todo = 0
        var doing = 0
        var done = 0
        var all = 0

        for (i in list){
            when(i.status){
                StatusList.TODO->todo++
                StatusList.DOING->doing++
                StatusList.DONE->done++
            }
        }
        all = todo + doing + done

        return Counter(todo, doing, done, all)
    }

    fun addItem(){
        CoroutineScope(Dispatchers.Default).launch {
            listAllHome.add(Item(0, StatusList.TODO, "ADD", ArrayList()))
            database.saveDatabaseListHome(listAllHome)
            getItem(currentList)
        }
    }
    fun deleteItem(position: Int){
        CoroutineScope(Dispatchers.Default).launch {
            listAllHome.removeAt(position)
            database.saveDatabaseListHome(listAllHome)
            getItem(currentList)
        }
    }
    fun editItem(position: Int, value: StatusList){
        CoroutineScope(Dispatchers.Default).launch {
            listAllHome[position].status = value
            database.saveDatabaseListHome(listAllHome)
            getItem(currentList)
        }
    }
    fun getItem(typeList: StatusList){
        CoroutineScope(Dispatchers.Main).launch {
            currentList = typeList

            listAllHome.clear()
            listAllHome.addAll(withContext(Dispatchers.Default) { database.getDatabaseListHome() })

            val currentList = withContext(Dispatchers.Default) {
                val dataReturn = ArrayList<Item>()

                if (typeList == StatusList.ALL){
                    dataReturn.addAll(listAllHome)
                    return@withContext dataReturn
                }else{
                    for (item in listAllHome){
                        if (item.status == typeList) dataReturn.add(item)
                    }
                    return@withContext dataReturn
                }
            }

            val counter = withContext(Dispatchers.Default){ counterList(listAllHome) }
            this@HomeViewModel.counter.value = counter
            listItem.value = currentList
        }
    }
}