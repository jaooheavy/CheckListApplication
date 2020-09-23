package jp.solucoes.checklistapplication.repository

import jp.solucoes.checklistapplication.model.Item

interface ListInterface {
    suspend fun getDatabaseListHome(): ArrayList<Item>
    suspend fun saveDatabaseListHome(list: ArrayList<Item>)
}
