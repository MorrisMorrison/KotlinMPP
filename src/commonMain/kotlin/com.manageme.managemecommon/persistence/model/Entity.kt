package com.manageme.managemecommon.persistence

import com.soywiz.klock.Date

expect interface IEntity {

    val createdAt: Date
}

expect abstract class Entity():IEntity{

}