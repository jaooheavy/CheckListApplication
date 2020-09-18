package jp.solucoes.checklistapplication.home.fragment.home

import com.orhanobut.hawk.Hawk
import jp.solucoes.checklistapplication.utils.ConstantsDatabase

object Fragment1Database {
    suspend fun getDatabaseListHome(): ArrayList<String>{
        return if (Hawk.contains(ConstantsDatabase.LIST_HOME)) Hawk.get(ConstantsDatabase.LIST_HOME) else ArrayList()
    }

    suspend fun saveDatabaseListHome(list: ArrayList<String>){
        Hawk.put(ConstantsDatabase.LIST_HOME, list)
    }
}