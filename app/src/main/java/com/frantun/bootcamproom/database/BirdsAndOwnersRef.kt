package com.frantun.bootcamproom.database

import androidx.room.Entity

@Entity(primaryKeys = ["birdId", "ownerId"])
data class BirdsAndOwnersRef(
    val birdId: Long,
    val ownerId: Long
)