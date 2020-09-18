package jp.solucoes.checklistapplication.model

data class ListHome(
    val id: Int,
    var status: StatusList,
    var name: String,
    var task: ArrayList<String>,
)