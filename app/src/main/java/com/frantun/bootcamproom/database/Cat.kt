package com.frantun.bootcamproom.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cat(
    @PrimaryKey val catId: Long,
    val catOwnerId: Long,
    val name: String
)