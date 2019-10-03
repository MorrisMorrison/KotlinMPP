package com.mwlltr.managemeclient.services

import com.mwlltr.managemeclient.model.IModel
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document


interface IViewModel {

    val model: IModel
    val service: IService

}


abstract class AbstractViewModel<T>(override val model: IModel, override val service: IService) : IViewModel {

    fun bind(elementId: String) {
        var input = document.getElementById(elementId)!! as HTMLInputElement
        var value = input.value
        model.properties[elementId] = value
    }

}