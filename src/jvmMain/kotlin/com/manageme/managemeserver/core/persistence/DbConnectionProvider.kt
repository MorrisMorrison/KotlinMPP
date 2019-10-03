package com.manageme.managemeserver.core.persistence

import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider


class DbConnectionProvider(val dbConfig: DbConfig) {


    private val _mongoClient: MongoClient by lazy {
        val fromRegistries: CodecRegistry = fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build())
        )

        val settings = MongoClientSettings.builder()
            .codecRegistry(fromRegistries)
            .build()

        MongoClients.create(settings)
    }

    fun getDatabaseConnection(): DbConnection {
        return DbConnection(
            dbConfig,
            _mongoClient,
            _mongoClient.getDatabase("manageme")
        )
    }

}