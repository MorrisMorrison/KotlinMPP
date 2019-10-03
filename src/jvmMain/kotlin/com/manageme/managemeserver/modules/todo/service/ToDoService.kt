package com.manageme.managemeserver.modules.todo.service

import com.manageme.managemeserver.core.persistence.IDao
import com.manageme.managemeserver.core.service.CrudService
import com.manageme.managemeserver.modules.todo.persistence.ToDo

class ToDoService(override var dao: IDao<ToDo>) : CrudService<ToDo>(dao) {
}