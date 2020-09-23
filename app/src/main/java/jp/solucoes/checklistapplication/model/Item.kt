package jp.solucoes.checklistapplication.model

data class Item(
    val id: Int,
    var status: StatusList,
    var name: String,
    var task: ArrayList<String>,
)