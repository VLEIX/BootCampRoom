package com.frantun.bootcamproom.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bird(
    @PrimaryKey val birdId: Long,
    val name: String
)