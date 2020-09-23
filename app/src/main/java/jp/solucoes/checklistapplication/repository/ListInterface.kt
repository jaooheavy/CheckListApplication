package jp.solucoes.checklistapplication.repository

import jp.solucoes.checklistapplication.model.ListHome

interface ListInterface {
    suspend fun getDatabaseListHome(): ArrayList<ListHome>
    suspend fun saveDatabaseListHome(list: ArrayList<ListHome>)
}
