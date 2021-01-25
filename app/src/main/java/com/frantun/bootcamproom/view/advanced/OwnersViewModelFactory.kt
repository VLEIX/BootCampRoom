package com.frantun.bootcamproom.view.advanced

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.frantun.bootcamproom.repositories.OwnerRepository

class OwnersViewModelFactory(private val repository: OwnerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OwnersViewModel::class.java)) {
            return OwnersViewModel(repository) as T
        } else {
            throw IllegalArgumentException("")
        }
    }
}