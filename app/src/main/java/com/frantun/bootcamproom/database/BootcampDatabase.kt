package com.frantun.bootcamproom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 2, exportSchema = true)
abstract class BootcampDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    private class WordDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { db ->
                scope.launch {
                    val wordDao = db.wordDao()

                    wordDao.deleteAll()

                    wordDao.insert(Word("Hello"))
                    wordDao.insert(Word("Bootcamp"))
                    wordDao.insert(Word("BCP"))
                }
            }
        }
    }

    companion object {

        private var INSTANCE: BootcampDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): BootcampDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BootcampDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
