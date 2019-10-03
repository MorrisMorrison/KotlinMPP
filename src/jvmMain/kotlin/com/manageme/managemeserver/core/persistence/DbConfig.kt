package com.manageme.managemeserver.core.persistence

import io.ktor.config.ApplicationConfig

// carrying the application config around might be a bad idea
class DbConfig {

    constructor(applicationConfig: ApplicationConfig)
    constructor()

    var host: String = "localhost"
    var port: Int = 27017
    var database: String = "manageme"

    init {
//        host = applicationConfig.propertyOrNull("persistence.mongodb.host").toString()
//        port = applicationConfig.propertyOrNull("persistence.mongodb.port").toString().toInt()
//        database = applicationConfig.propertyOrNull("persistence.mongodb.database").toString()
    }

}