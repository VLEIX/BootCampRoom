package com.frantun.bootcamproom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(@PrimaryKey @ColumnInfo(name = "word") var word: String, @ColumnInfo(name = "sub_word") var subWord: String = "cafe")