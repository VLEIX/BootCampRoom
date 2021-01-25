package com.frantun.bootcamproom.repositories

import androidx.annotation.WorkerThread
import com.frantun.bootcamproom.database.*
import kotlinx.coroutines.flow.Flow

class OwnerRepository(private val ownerDAO: OwnerDao) {

    val dogsAndOwners: Flow<List<DogAndOwner>> = ownerDAO.getDogAndOwner()
    val catsAndOwners: Flow<List<CatsAndOwner>> = ownerDAO.getCatsAndOwner()
    val birdsAndOwners: Flow<List<BirdsAndOwners>> = ownerDAO.getBirdsAndOwner()

    @WorkerThread
    suspend fun insert(owner: Owner) {
        ownerDAO.insert(owner)
    }
}