package com.frantun.bootcamproom.repositories

import com.frantun.bootcamproom.database.Word
import com.frantun.bootcamproom.database.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun delete(word: Word) {
        wordDao.delete(word)
    }
}