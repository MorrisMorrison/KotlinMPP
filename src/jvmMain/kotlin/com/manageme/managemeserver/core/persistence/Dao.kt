package com.manageme.managemeserver.core.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import org.bson.conversions.Bson
import org.bson.types.ObjectId

interface IDao<T : IEntity> {

    val dbConnectionProvider: DbConnectionProvider
    var dbConnection: DbConnection

    fun get(): List<T>
    fun get(id: String): T
    fun create(entity: T): String
    fun update(entity: T)
    fun delete(id: String)

}

abstract class Dao<T : IEntity>(override var dbConnectionProvider: DbConnectionProvider, var classOfT: Class<T>) :
    IDao<T> {

    override var dbConnection: DbConnection
        get() = dbConnectionProvider.getDatabaseConnection()
        set(value) {
            dbConnection = value
        }


    override fun get(id: String): T {
        val collection: MongoCollection<T> =
            dbConnection.mongoDatabase.getCollection(classOfT.simpleName.toLowerCase(), classOfT)
        var filter: Bson = eq("_id", ObjectId(id))
        val result: T? = collection.find(filter).first()
        return result!!
    }

    override fun get(): List<T> {
        val collection: MongoCollection<T> =
            dbConnection.mongoDatabase.getCollection(classOfT.simpleName.toLowerCase(), classOfT)
        val result: List<T> = collection.find().toList()
        return result
    }

    override fun create(entity: T): String {
        val collection: MongoCollection<T> =
            dbConnection.mongoDatabase.getCollection(classOfT.simpleName.toLowerCase(), classOfT)
        collection.insertOne(entity)

        return entity.id.toString();
    }

    override fun update(entity: T) {
        val collection: MongoCollection<T> =
            dbConnection.mongoDatabase.getCollection(classOfT.simpleName.toLowerCase(), classOfT)
        var filter: Bson = eq("_id", entity.id)
        collection.replaceOne(filter, entity)
    }

    override fun delete(id: String) {
        val collection: MongoCollection<T> =
            dbConnection.mongoDatabase.getCollection(classOfT.simpleName.toLowerCase(), classOfT)
        var filter: Bson = eq("_id", ObjectId(id))
        collection.deleteOne(filter)
    }

}