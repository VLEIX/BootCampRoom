package com.frantun.bootcamproom.database

import androidx.room.Embedded
import androidx.room.Relation

data class CatsAndOwner(
    @Embedded val owner: Owner,
    @Relation(
        parentColumn = "ownerId",
        entityColumn = "catOwnerId"
    )
    val cats: List<Cat>
)