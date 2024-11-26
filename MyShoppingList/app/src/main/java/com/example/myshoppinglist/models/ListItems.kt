package com.example.myshoppinglist.models

data class ListItems(
    var DocId : String?,
    val name:String?,
    val owners: List<String>?) {

    constructor() : this(null,null,null)

}