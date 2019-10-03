package com.manageme.managemeserver.core.controller

import com.manageme.managemeserver.core.persistence.IEntity
import com.manageme.managemeserver.core.service.ICrudService
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import kotlin.reflect.KClass

interface IController<T : IEntity> {
    var service: ICrudService<T>
}

interface ICrudController<T : IEntity> :
    IController<T> {
    suspend fun getAll(call: ApplicationCall)
    suspend fun get(call: ApplicationCall)
    suspend fun create(call: ApplicationCall)
    suspend fun update(call: ApplicationCall)
    suspend fun delete(call: ApplicationCall)
}

abstract class CrudController<T : IEntity>(override var service: ICrudService<T>, var classOfT: KClass<T>) :
    ICrudController<T> {

    override suspend fun getAll(call: ApplicationCall) {
        val list: List<T> = service.get()

        if (list.isEmpty()) {
            call.response.status(HttpStatusCode.NoContent)
        }

        call.respond(HttpStatusCode.OK, list)
    }

    override suspend fun get(call: ApplicationCall) {
        val id: String = call.parameters["id"] as String
        val entity: T = service.get(id)

        call.respond(HttpStatusCode.OK, entity)
    }

    override suspend fun create(call: ApplicationCall) {
        val entity: T = call.receive(classOfT)
        service.create(entity)

        call.respond(HttpStatusCode.Created)
    }


    override suspend fun update(call: ApplicationCall) {
        val entity: T = call.receive(classOfT)
        service.update(entity)

        call.respond(HttpStatusCode.OK)
    }

    override suspend fun delete(call: ApplicationCall) {
        val id: String = call.parameters["id"] as String
        service.delete(id)

        call.respond(HttpStatusCode.OK)
    }
}