package com.manageme.managemeserver.modules.todo.persistence

import com.manageme.managemeserver.core.persistence.Entity

data class ToDo(var content: String = "") : Entity() {
}