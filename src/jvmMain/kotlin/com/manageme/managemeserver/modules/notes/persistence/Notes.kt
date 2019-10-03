package com.manageme.managemeserver.modules.notes.persistence

import com.manageme.managemeserver.core.persistence.Entity

data class Notes(override var name: String) : Entity() {
}