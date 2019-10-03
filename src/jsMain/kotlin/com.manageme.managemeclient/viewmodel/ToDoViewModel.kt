package com.mwlltr.managemeclient.viewmodel

import com.mwlltr.managemeclient.model.IToDo
import com.mwlltr.managemeclient.model.ToDo
import com.mwlltr.managemeclient.model.ToDoModel
import com.mwlltr.managemeclient.services.AbstractViewModel
import com.mwlltr.managemeclient.services.ToDoService

class ToDoViewModel(private val toDoModel: ToDoModel, private val toDoService: ToDoService) :
    AbstractViewModel<ToDo>(toDoModel, toDoService) {

    fun add(inputId: String) {
        suspend {
            toDoService.create(ToDo(inputId)).onSuccess {
                toDoModel.todos.add(ToDo(inputId))
                console.log("Success!!!")
            }
        }

        console.log(toDoModel.properties[inputId])
    }

    suspend fun loadToDos(): MutableList<IToDo> {
        toDoModel.todos = toDoService.getAll() as MutableList<IToDo>
        return toDoModel.todos
    }
}