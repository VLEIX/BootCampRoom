package com.frantun.bootcamproom.database

import androidx.room.*
import com.frantun.bootcamproom.database.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface OwnerDao {

    @Transaction
    @Query("SELECT * FROM Owner")
    fun getDogAndOwner(): Flow<List<DogAndOwner>>

    @Transaction
    @Query("SELECT * FROM Owner")
    fun getCatsAndOwner(): Flow<List<CatsAndOwner>>

    @Transaction
    @Query("SELECT * FROM Owner")
    fun getBirdsAndOwner(): Flow<List<BirdsAndOwners>>

    @Insert
    suspend fun insert(owner: Owner)

    @Insert
    suspend fun insertBirdAndOwnerRef(birdsAndOwnersRef: BirdsAndOwnersRef)
}
