package com.mwlltr.managemeclient.services

import com.mwlltr.managemeclient.model.IToDo
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.JsClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url

interface IService {
    val client: HttpClient
}

interface ICrudService<T> : IService {
    suspend fun get(id: String): T
    suspend fun getAll(): List<T>
    suspend fun create(entity: T): ICrudService<T>
    suspend fun update(entity: T)
    suspend fun delete(id: String)

    fun onSuccess(onSuccessFunction: () -> Unit)
    fun onError(onSuccessFunction: () -> Unit)

}

class ToDoService : ICrudService<IToDo> {

    override val client: HttpClient = HttpClient(JsClient())


    override suspend fun get(id: String): IToDo {
        val response: String = client.get("http://0.0.0.0:8080/todo/$id")
        val result: IToDo = JSON.parse(response)

        console.log(result)

        return result
    }

    override suspend fun getAll(): List<IToDo> {
        val response: String = client.get("http://0.0.0.0:8080/todo/")
        val result: Array<IToDo> = JSON.parse(response)

        console.log(result)

        return result.toList()
    }

    override suspend fun create(entity: IToDo): ICrudService<IToDo> {
        client.post<String> {
            url("http://0.0.0.0:8080/todo/")
            body = JSON.stringify(entity)
        }

        return this
    }

    override suspend fun update(entity: IToDo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(onSuccessFunction: () -> Unit) {
        onSuccessFunction.invoke()
    }

    override fun onError(onSuccessFunction: () -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}