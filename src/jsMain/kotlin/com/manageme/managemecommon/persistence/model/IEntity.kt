package com.manageme.managemecommon.persistence

import com.soywiz.klock.Date

actual interface IEntity {
    actual val createdAt: Date

}

actual abstract class Entity actual constructor() : IEntity {

}