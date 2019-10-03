package com.manageme.managemeserver.core.persistence

import org.bson.types.ObjectId
import java.time.Instant
import java.util.*

interface IEntity {
    val id: ObjectId
    var name: String
    var createdAt: Date
    var updatedAt: Date
}

abstract class Entity() : IEntity {
    override var name: String = ""
    @Transient
    override var id: ObjectId = ObjectId()
    @Transient
    override var createdAt: Date = Date.from(Instant.now())
    @Transient
    override var updatedAt: Date = Date.from(Instant.now())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Entity

        if (name != other.name) return false
        if (id != other.id) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }


}

