package com.manageme.managemeserver.modules.notes.service

import com.manageme.managemeserver.core.persistence.IDao
import com.manageme.managemeserver.core.service.CrudService
import com.manageme.managemeserver.modules.notes.persistence.Notes

class NotesService(override var dao: IDao<Notes>) : CrudService<Notes>(dao) {
}