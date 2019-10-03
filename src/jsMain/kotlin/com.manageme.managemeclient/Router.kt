package com.mwlltr.managemeclient

import com.mwlltr.managemeclient.components.HomeComponent
import com.mwlltr.managemeclient.components.ToDoComponent
import kotlinx.html.dom.create
import kotlinx.html.js.div
import kotlin.browser.document

enum class Routes {
    HOME,
    TODO,
    NOTES
}

class Router(private var toDoComponent: ToDoComponent) {

    fun renderToDo() {
        console.log(toDoComponent)
        clearContent()
        toDoComponent.rootId = "todo"
        toDoComponent.render()

    }


    fun route(route: Routes) {
        when (route) {
            Routes.HOME -> renderHome()
            Routes.TODO -> renderToDo()
            Routes.NOTES -> renderNotes()
        }

    }

    private fun renderHome() {
        clearContent()
        HomeComponent("home").render()
    }


    private fun renderNotes() {
        clearContent()
        document.getElementById("content")!!.append(document.create.div {
            +"Notes"
        })
    }

    private fun clearContent() {
        document.getElementById("content")!!.innerHTML = ""
    }

}