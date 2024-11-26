package com.example.myshoppinglist.models

class Item (
    var DocId : String?,
    var name : String?,
    var qtd : Double?,
    var checked : Boolean = false) {

    constructor() : this(null,null,null, false)
}