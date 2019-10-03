import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.erased.bind
import com.github.salomonbrys.kodein.erased.instance
import com.github.salomonbrys.kodein.erased.singleton
import com.github.salomonbrys.kodein.newInstance
import com.mwlltr.managemeclient.Router
import com.mwlltr.managemeclient.Routes
import com.mwlltr.managemeclient.components.DrawerComponent
import com.mwlltr.managemeclient.components.FooterComponent
import com.mwlltr.managemeclient.components.NavBarComponent
import com.mwlltr.managemeclient.components.ToDoComponent
import com.mwlltr.managemeclient.model.ToDoModel
import com.mwlltr.managemeclient.services.ToDoService
import com.mwlltr.managemeclient.viewmodel.ToDoViewModel
import kotlin.browser.document

fun main(args: Array<String>) {

    var kodein = setupDependencyInjection()
    initBasicStructure(kodein)


    val router = kodein.instance<Router>()
    router.route(Routes.HOME)

    // init Materialize CSS stuff
    refreshMaterialize()
}

fun initBasicStructure(kodein: Kodein) {
//fun main() {
    document.addEventListener("DOMContentLoaded", {
        document.getElementById("header")!!.append(NavBarComponent("navbar").skeleton)
        document.getElementById("drawer")!!.append(kodein.newInstance {
            DrawerComponent(
                instance(),
                "drawer"
            )
        }.skeleton)
        document.getElementById("footer")!!.append(FooterComponent("footer").skeleton)
    })
//}
}

fun setupDependencyInjection(): Kodein {
    val modules = Kodein.Module {
        bind<ToDoModel>() with singleton { ToDoModel() }
        bind<ToDoService>() with singleton { ToDoService() }
        bind<ToDoViewModel>() with singleton { ToDoViewModel(instance(), instance()) }
        bind<ToDoComponent>() with singleton { ToDoComponent(instance(), "todo") }
        bind<Router>() with singleton { Router(instance()) }
        bind<DrawerComponent>() with singleton { DrawerComponent(instance(), "drawer") }
    }

    val kodein = Kodein {
        import(modules)
    }

    return kodein
}

fun refreshMaterialize() {
    js("M.AutoInit();")
}


