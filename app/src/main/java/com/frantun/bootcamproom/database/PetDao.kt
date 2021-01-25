package com.frantun.bootcamproom.database

import androidx.room.*
import com.frantun.bootcamproom.database.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {

    @Insert
    suspend fun insertDog(dog: Dog)

    @Insert
    suspend fun insertCat(cat: Cat)

    @Insert
    suspend fun insertBird(bird: Bird)
}
