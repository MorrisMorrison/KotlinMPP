package Components

import com.mwlltr.managemeclient.Router
import com.mwlltr.managemeclient.services.IViewModel
import kotlinx.html.dom.create
import kotlinx.html.id
import kotlinx.html.js.div
import org.w3c.dom.HTMLElement
import org.w3c.dom.parsing.DOMParser
import kotlin.browser.document

// 3 types of components
// 1. simple component without view model
// 2. complex component with view model
// 3. routing component with router

interface IComponent {

    val rootId: String
    val rootName: String
    var skeleton: HTMLElement

    fun render()

}

interface ISimpleComponent {
    var htmlTemplate: String
}

interface IComplexComponent : IComponent {
    val viewModel: IViewModel

    var styles: MutableMap<String, String>
    var classes: MutableList<String>

    fun addStyle(style: Pair<String, String>)
    fun removeStyle(style: Pair<String, String>)

    fun addClass(className: String)
    fun removeClass(className: String)
}


interface IRoutingComponent : IComponent {
    var router: Router
}

abstract class Component() : IComponent {

    override var rootId: String = ""
    override var rootName: String = ""
        get() {
            if (field == "") {
                return rootId
            }
            return rootName
        }
    override var skeleton: HTMLElement = document.create.div { id = rootId }
}

abstract class SimpleComponent : Component(), ISimpleComponent {

    override var htmlTemplate: String = ""
    override var skeleton: HTMLElement = document.create.div { id = rootId }
        get() {
            if (!field.hasChildNodes() && htmlTemplate != "") {
                return DOMParser().parseFromString(htmlTemplate, "text/html").body!!
            }
            return skeleton
        }


    override fun render() {
    }

}

abstract class ComplexComponent(override var viewModel: IViewModel) : Component(), IComplexComponent {

    override var styles: MutableMap<String, String> = mutableMapOf()
    override var classes: MutableList<String> = mutableListOf()

    override fun addStyle(style: Pair<String, String>) {
        styles[style.first] = style.second
    }

    override fun removeStyle(style: Pair<String, String>) {
        styles.remove(style.first)
    }

    override fun addClass(className: String) {
        classes.add(className)
    }

    override fun removeClass(className: String) {
        classes.remove(className)
    }
}

abstract class RoutingComponent(override var router: Router) : Component(), IRoutingComponent


//