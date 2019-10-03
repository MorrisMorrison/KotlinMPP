package com.manageme.managemeserver.core.persistence

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase

class DbConnection(var dbConfig: DbConfig, var mongoClient: MongoClient, var mongoDatabase: MongoDatabase) {

}