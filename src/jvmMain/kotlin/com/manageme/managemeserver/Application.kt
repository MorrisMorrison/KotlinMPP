package com.manageme.managemeserver

import com.manageme.managemeserver.core.persistence.DbConfig
import com.manageme.managemeserver.core.persistence.DbConnectionProvider
import com.manageme.managemeserver.core.persistence.IDao
import com.manageme.managemeserver.core.service.IService
import com.manageme.managemeserver.modules.notes.controller.NotesController
import com.manageme.managemeserver.modules.todo.controller.ToDoController
import com.manageme.managemeserver.modules.todo.persistence.ToDo
import com.manageme.managemeserver.modules.todo.persistence.ToDoDao
import com.manageme.managemeserver.modules.todo.service.ToDoService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.routing.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get
import java.text.DateFormat

fun main(args: Array<String>) {
//    val module = module{
//        single{ToDoController(get())}
//        single{ToDoService()}
//    }
//    startKoin{
//        modules(module)
//    }
    io.ktor.server.netty.EngineMain.main(args)
}


/*fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondText("Hello World!", ContentType.Text.Plain)
            }
            get("/demo") {
                call.respondText("HELLO WORLD!")
            }
        }
    }
    server.start(wait = true)
}*/

fun Application.main() {

    routing {
        static {
            resource("/static/kotlin/1.3.50/kotlin.js", "kotlin/1.3.50/kotlin.js")
            resource("/static/kotlinx-html-js/0.6.11/kotlinx-html-js.js", "kotlinx-html-js/0.6.11/kotlinx-html-js.js")
            resource("/static/kotlinx-io/0.1.14/kotlinx-io.js", "kotlinx-io/0.1.14/kotlinx-io.js")
            resource("/static/kotlin-test/1.3.50/kotlin-test.js", "kotlin-test/1.3.50/kotlin-test.js")
            resource(
                "/static/kotlinx-atomicfu/0.12.11/kotlinx-atomicfu.js",
                "kotlinx-atomicfu/0.12.11/kotlinx-atomicfu.js"
            )
            resource(
                "/static/kotlinx-coroutines-core/1.3.2/kotlinx-coroutines-core.js",
                "kotlinx-coroutines-core/1.3.2/kotlinx-coroutines-core.js"
            )
            resource(
                "/static/kotlinx-coroutines-io/0.1.14/kotlinx-coroutines-io.js",
                "kotlinx-coroutines-io/0.1.14/kotlinx-coroutines-io.js"
            )
            resource("/static/ktor-ktor-utils/1.2.4/ktor-ktor-utils.js", "ktor-ktor-utils/1.2.4/ktor-ktor-utils.js")
            resource("/static/ktor-ktor-http/1.2.4/ktor-ktor-http.js", "ktor-ktor-http/1.2.4/ktor-ktor-http.js")
            resource(
                "/static/ktor-ktor-http-cio/1.2.4/ktor-ktor-http-cio.js",
                "ktor-ktor-http-cio/1.2.4/ktor-ktor-http-cio.js"
            )
            resource(
                "/static/ktor-ktor-client-js/1.2.4/ktor-ktor-client-js.js",
                "ktor-ktor-client-js/1.2.4/ktor-ktor-client-js.js"
            )
            resource(
                "/static/ktor-ktor-client-core/1.2.4/ktor-ktor-client-core.js",
                "ktor-ktor-client-core/1.2.4/ktor-ktor-client-core.js"
            )
            resource(
                "/static/ktor-ktor-client-json/1.2.4/ktor-ktor-client-json.js",
                "ktor-ktor-client-json/1.2.4/ktor-ktor-client-json.js"
            )
            resource("/static/kodein-js_main/4.1.0/kodein-js_main.js", "kodein-js_main/4.1.0/kodein-js_main.js")
            resource("/static/ManageMeMPP.js", "ManageMeMPP.js")
            resource("styles.less")
            resource("/", "index.html")
        }
    }


    val persistence = module {
        single {
            DbConfig(environment.config)
        }
        single {
            DbConnectionProvider(get())
        }

    }

    val toDo = module {
        single<IDao<ToDo>>(named("toDoDao")) {
            ToDoDao(get())
        }
        single<IService<ToDo>>(named("toDoService")) {
            ToDoService(get(named("toDoDao")))
        }
        single { ToDoController(get(named("toDoService"))) }
    }

//    val notes = module{
//        single<IDao<Notes>>(named("notesDao")){
//            NotesDao()
//        }
//        single<IService>(named("notesService")){
//            NotesService(get(named("notesDao")))}
//        single{NotesController(get(named("notesService")))}
//
//    }

    install(CORS) {
        anyHost()
    }




    install(Koin) {
        //        modules(listOf(toDo, notes))
        modules(listOf(toDo, persistence))
    }

    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }
}

fun Application.todo() {

    val modulePrefix = "/todo/"
    val toDoController: ToDoController = get()

    routing {
        get(modulePrefix) {
            toDoController.getAll(call)
        }
        get(modulePrefix + "{id}") {
            toDoController.get(call)
        }
        post(modulePrefix) {
            toDoController.create(call)
        }
        put(modulePrefix) {
            toDoController.update(call)
        }
        delete(modulePrefix + "{id}") {
            toDoController.delete(call)
        }
    }

}

fun Application.notes() {

    val modulePrefix = "/notes/"
    val notesController: NotesController = get()

    routing {
        get(modulePrefix + "test") {
            notesController.getAll(call)
        }
    }
}

fun Application.finance() {

}


