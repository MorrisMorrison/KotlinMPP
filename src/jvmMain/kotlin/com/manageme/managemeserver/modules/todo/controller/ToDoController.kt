package com.manageme.managemeserver.modules.todo.controller

import com.manageme.managemeserver.core.controller.CrudController
import com.manageme.managemeserver.core.service.ICrudService
import com.manageme.managemeserver.modules.todo.persistence.ToDo

class ToDoController(service: ICrudService<ToDo>) : CrudController<ToDo>(service, ToDo::class) {

}