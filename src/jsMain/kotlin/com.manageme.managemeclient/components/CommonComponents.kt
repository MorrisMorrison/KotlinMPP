package com.mwlltr.managemeclient.components

import Components.Component
import Components.RoutingComponent
import Components.SimpleComponent
import com.mwlltr.managemeclient.Router
import com.mwlltr.managemeclient.Routes
import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLSpanElement
import kotlin.browser.document

class NavBarComponent(override var rootId: String) : SimpleComponent() {
    val brand = "ManageMe"
    override var htmlTemplate: String = """<nav>
    <div class="nav-wrapper header">
      <a href="#" class="brand-logo" style="padding-left:1%;">$brand</a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li>
            <a class='dropdown-trigger'  href='#' data-target='dropdown1'>Username</a>
      <ul id='dropdown1' class='dropdown-content' style='width:10px;'>
        <li><a href="#!"><i class="material-icons">settings</i>Settings</a></li>
        <li class="divider" tabindex="-1"></li>
        <li><a href="#!"><i class="material-icons">logout</i>Logout</a></li>
      </ul>
  </li>
      </ul>
    </div>
  </nav>"""
}

class FooterComponent(override var rootId: String) : SimpleComponent() {
    override var htmlTemplate: String = """
        <footer class="page-footer footer">
          <div class="container">
            <div class="row">
              <div class="col l6 s12">
                <h5 class="white-text">ManageMe</h5>
                <p class="grey-text text-lighten-4">You can contact us <a href="#">here</a>.</p>
              </div>
              <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Links</h5>
                <ul>
                  <li><a class="grey-text text-lighten-3" href="#!">GitHub</GitHub></li>
                  <li><a class="grey-text text-lighten-3" href="#!">Contact</a></li>
                  <li><a class="grey-text text-lighten-3" href="#!">Impressum</a></li>
                </ul>
              </div>
            </div>
          </div>
          <div class="footer-copyright">
            <div class="container">
            © 2019 ManageMe
            </div>
          </div>
        </footer>"""
}

class DrawerComponent(override var router: Router, override var rootId: String) : RoutingComponent(router) {

    override var skeleton = document.create.div {
        ul {
            classes = setOf("collection", "drawer-nav")
            li {
                classes = setOf("collection-item", "drawer-li")
                style = "display:flex"
                button {
                    classes = setOf("drawer-button", "transparent")
                    i {
                        classes = setOf("material-icons", "drawer-button-icon")
                        +"home"
                    }
                    span {
                        classes = setOf("drawer-button-text")
                        +"Home"
                    }
                    onClickFunction = {
                        router.route(Routes.HOME)
                    }
                }
            }
            li {
                classes = setOf("collection-item", "drawer-li")
                style = "display:flex"
                button {
                    classes = setOf("drawer-button", "transparent")
                    i {
                        classes = setOf("material-icons", "drawer-button-icon")
                        +"check_box"
                    }
                    span {
                        classes = setOf("drawer-button-text")
                        +"ToDo"
                    }
                    onClickFunction = {
                        router.route(Routes.TODO)
                    }
                }

            }
            li {
                classes = setOf("collection-item", "drawer-li")
                style = "display:flex"
                button {
                    classes = setOf("drawer-button", "transparent")
                    i {
                        classes = setOf("material-icons", "drawer-button-icon")
                        +"notes"
                    }
                    span {
                        classes = setOf("drawer-button-text")
                        +"Notes"
                    }
                    onClickFunction = {
                        router.route(Routes.NOTES)
                    }
                }
            }

        }
    } as HTMLElement

    override fun render() {
    }
}


class CardComponent(override var rootId: String, var parentId: String) : Component() {

    private var cardTitle: HTMLElement = document.create.span { }
    private var cardContent: HTMLElement = document.create.div { }

    override var skeleton = document.create.div {
        id = "$rootId-card"
        classes = setOf("card")
        div {
            classes = setOf("card-content")
            div {
                id = "$rootId-card-title"
                classes = setOf("card-title")
            }
            div {
                id = "$rootId-card-content"
            }
        }
    } as HTMLElement

    fun setCardTitle(title: HTMLSpanElement): CardComponent {
        cardTitle = title
        return this
    }

    fun setCardContent(content: HTMLDivElement): CardComponent {
        cardContent = content
        return this
    }

    override fun render() {
        document.getElementById(parentId)!!.append(skeleton)
        document.getElementById("$rootId-card-title")!!.append(cardTitle)
        document.getElementById("$rootId-card-content")!!.append(cardContent)
    }
}