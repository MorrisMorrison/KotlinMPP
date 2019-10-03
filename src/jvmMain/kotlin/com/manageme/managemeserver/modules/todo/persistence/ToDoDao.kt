package com.manageme.managemeserver.modules.todo.persistence

import com.manageme.managemeserver.core.persistence.Dao
import com.manageme.managemeserver.core.persistence.DbConnectionProvider

class ToDoDao(override var dbConnectionProvider: DbConnectionProvider) :
    Dao<ToDo>(dbConnectionProvider, ToDo::class.java) {

}