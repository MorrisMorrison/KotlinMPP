package com.mwlltr.managemeclient.model

interface IToDo {
    val name: String
    val content: String
}

interface IToDoModel : IModel {
    var todos: MutableList<IToDo>
}

class ToDoModel : IToDoModel {
    override var properties: MutableMap<String, String> = mutableMapOf()
    override var todos: MutableList<IToDo> = mutableListOf()
}

class ToDo(override val name: String, override val content: String = "") : IToDo {
}


