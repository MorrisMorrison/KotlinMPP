package com.mwlltr.managemeclient.components

import Components.Component
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.dom.create
import kotlinx.html.id
import kotlinx.html.js.div
import kotlinx.html.js.span
import org.w3c.dom.HTMLElement
import kotlin.browser.document

class HomeComponent(override var rootId: String) : Component() {

    override var skeleton: HTMLElement = document.create.div {
        id = rootId
        classes = setOf("row")
        div {
            id = "home-main"
            classes = setOf("col s12")

        }
        div {
            id = "home-todo"
            classes = setOf("col s6")

        }
        div {
            id = "home-notes"
            classes = setOf("col s6")

        }
    }

    override fun render() {
        document.getElementById("content")!!.append(skeleton)

        CardComponent("home-main", "home-main")
            .setCardTitle(document.create.span { +"Overview" })
            .setCardContent(document.create.div {
                +"HomeContent"
            }).render()

        CardComponent("home-todo", "home-todo")
            .setCardTitle(document.create.span { +"Due Today" })
            .setCardContent(document.create.div {
                +"ToDoContent"
            })
            .render()

        CardComponent("home-notes", "home-notes")
            .setCardTitle(document.create.span { +"Recent Notes" })
            .setCardContent(document.create.div {
                +"NotesContent"
            })
            .render()

    }

}