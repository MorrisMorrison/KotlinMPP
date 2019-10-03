package com.manageme.managemeserver.core.service

import com.manageme.managemeserver.core.persistence.IDao
import com.manageme.managemeserver.core.persistence.IEntity

interface IService<T : IEntity> {
    var dao: IDao<T>
}

interface ICrudService<T : IEntity> :
    IService<T> {
    fun get(): List<T>
    fun get(id: String): T
    fun create(entity: T)
    fun update(entity: T)
    fun delete(id: String)
}

abstract class CrudService<T : IEntity>(override var dao: IDao<T>) :
    ICrudService<T> {
    override fun get(): List<T> {
        return dao.get()
    }

    override fun get(id: String): T {
        val entity: T = dao.get(id)
        return entity
    }

    override fun create(entity: T) {
        dao.create(entity)
    }

    override fun update(entity: T) {
        dao.update(entity)
    }

    // TODO only 1 call is neccessary
    override fun delete(id: String) {
        dao.delete(id)
    }
}