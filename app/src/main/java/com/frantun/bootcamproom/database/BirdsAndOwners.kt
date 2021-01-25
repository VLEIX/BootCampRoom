package com.frantun.bootcamproom.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class BirdsAndOwners(
    @Embedded val owner: Owner,
    @Relation(
        parentColumn = "ownerId",
        entityColumn = "birdId",
        associateBy = Junction(BirdsAndOwnersRef::class)
    )
    val birds: List<Bird>
)