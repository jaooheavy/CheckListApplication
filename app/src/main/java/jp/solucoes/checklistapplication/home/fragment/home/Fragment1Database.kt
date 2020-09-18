package jp.solucoes.checklistapplication.home.fragment.home

import com.orhanobut.hawk.Hawk
import jp.solucoes.checklistapplication.model.ListHome
import jp.solucoes.checklistapplication.utils.ConstantsDatabase

object Fragment1Database {
    suspend fun getDatabaseListHome(): ArrayList<ListHome>{
        return if (Hawk.contains(ConstantsDatabase.LIST_HOME)) Hawk.get(ConstantsDatabase.LIST_HOME) else ArrayList()
    }

    suspend fun saveDatabaseListHome(list: ArrayList<ListHome>){
        Hawk.put(ConstantsDatabase.LIST_HOME, list)
    }
}