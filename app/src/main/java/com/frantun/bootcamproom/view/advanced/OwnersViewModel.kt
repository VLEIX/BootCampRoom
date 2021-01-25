package com.frantun.bootcamproom.view.advanced

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.frantun.bootcamproom.database.BirdsAndOwners
import com.frantun.bootcamproom.database.CatsAndOwner
import com.frantun.bootcamproom.database.DogAndOwner
import com.frantun.bootcamproom.repositories.OwnerRepository

class OwnersViewModel (private val repository: OwnerRepository) : ViewModel() {
    val dogsAndOwners: LiveData<List<DogAndOwner>> = repository.dogsAndOwners.asLiveData()
    val catsAndOwners: LiveData<List<CatsAndOwner>> = repository.catsAndOwners.asLiveData()
    val birdsAndOwners: LiveData<List<BirdsAndOwners>> = repository.birdsAndOwners.asLiveData()
}