package com.frantun.bootcamproom.view.basic

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.frantun.bootcamproom.database.Word
import com.frantun.bootcamproom.repositories.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun delete(word:Word) = viewModelScope.launch {
        repository.delete(word)
    }
}