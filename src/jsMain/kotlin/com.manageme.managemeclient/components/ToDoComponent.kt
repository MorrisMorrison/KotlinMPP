package com.mwlltr.managemeclient.components

import Components.ComplexComponent
import com.manageme.managemecommon.persistence.TestModel
import com.mwlltr.managemeclient.model.IToDo
import com.mwlltr.managemeclient.viewmodel.ToDoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.html.js.div
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.span
import org.w3c.dom.HTMLElement
import refreshMaterialize
import kotlin.browser.document

class ToDoComponent(private var toDoViewModel: ToDoViewModel, override var rootId: String) :
    ComplexComponent(toDoViewModel) {

    val inputId = "add-todo"

    override var skeleton: HTMLElement = document.create.div {
        id = "$rootId"
        classes = setOf("row")
        div {
            id = "$rootId-main"
            classes = setOf("col s6")
        }
        div {
            id = "$rootId-today"
            classes = setOf("col s6")
        }
        div {
            id = "$rootId-week"
            classes = setOf("col s6")
        }
    }


    override fun render() {
        document.getElementById("content")!!.append(skeleton)

        CardComponent("$rootId-main", "$rootId-main").setCardTitle(document.create.span {
            h3 {
                +"ToDo"
            }
            span {
                +"29.09.2019"
            }
        })
            .setCardContent(document.create.div {
                ul {
                    id = "todo-list"
                    classes = setOf("collection", "no-borders")
                }
                div {
                    classes = setOf("flex", "items-center")
                    input {
                        id = inputId
                        name = inputId
                        style = "width:95%; padding-left:3%;"
                        type = InputType.text
                        onChangeFunction = {
                            toDoViewModel.bind(inputId)
                        }
                    }
                    button {
                        classes = setOf("btn", "add-button")
                        style = "margin-left:1%"
                        +"Add"
                        onClickFunction = {
                            add()
                        }
                    }
                }
            })
            .render()

        CardComponent("$rootId-today", "$rootId-today").setCardTitle(document.create.span {
            +"Due Today"
        })
            .setCardContent(document.create.div {
                ul {
                    li {
                        label {
                            input {
                                type = InputType.checkBox
                            }
                            span {
                                classes = setOf("checkbox-text")
                                +"Red"
                            }
                        }
                    }
                }
            })
            .render()

        CardComponent("$rootId-week", "$rootId-week").setCardTitle(document.create.span {
            +"Due this week"
        })
            .setCardContent(document.create.div {
            })
            .render()

        loadToDos()
    }


    fun add(name: String = "") {

        var todo = if (name != ""){
            name
        }else{
            toDoViewModel.model.properties[inputId]!!
        }

        console.log("Name: " + name)
        console.log("ToDo: " + todo)

        document.getElementById("todo-list")!!.append(document.create.li {
            classes = setOf("collection-item", "no-borders")
            div {
                +todo!!
                a {
                    classes = setOf("dropdown-trigger", "secondary-content")
                    attributes["data-target"] = "$todo-dropdown"
                    i {
                        classes = setOf("material-icons")
                        +"more_vert"
                    }
                }
                ul {
                    id = "$todo-dropdown"
                    classes = setOf("dropdown-content")
                    li {
                        a {
                            href = "#!"
                            i {
                                classes = setOf("material-icons")
                                +"delete"
                            }
                            +"Delete"
                        }
                    }
                    li {
                        a {
                            href = "#!"
                            i {
                                classes = setOf("material-icons")
                                +"settings"
                            }
                            +"Settings"
                        }
                    }
                }
            }
        })

        toDoViewModel.add(inputId)

        refreshMaterialize()
    }

    fun loadToDos() {
        GlobalScope.launch {
            val loadToDos: MutableList<IToDo> = toDoViewModel.loadToDos()
            loadToDos.forEach {
                add(it.name)
            }
        }
    }
}
