package com.frantun.bootcamproom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class, Owner::class, Dog::class, Cat::class, Bird::class, BirdsAndOwnersRef::class], version = 2, exportSchema = true)
abstract class BootcampDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
    abstract fun ownerDao(): OwnerDao
    abstract fun petDao(): PetDao

    private class WordDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { db ->
                scope.launch {
                    val wordDao = db.wordDao()
                    val ownerDao = db.ownerDao()
                    val petDao = db.petDao()

                    wordDao.deleteAll()

                    wordDao.insert(Word("Hello"))
                    wordDao.insert(Word("Bootcamp"))
                    wordDao.insert(Word("BCP"))

                    ownerDao.insert(Owner(1, "Flavio"))
                    ownerDao.insert(Owner(2, "Eren"))
                    ownerDao.insert(Owner(3, "Goku"))

                    petDao.insertDog(Dog(1, 1, "Bobby"))
                    petDao.insertDog(Dog(2, 3, "Firulais"))

                    petDao.insertCat(Cat(1, 1, "Michi"))
                    petDao.insertCat(Cat(2, 1, "Felix"))
                    petDao.insertCat(Cat(3, 3, "Comotu"))

                    petDao.insertBird(Bird(1, "Piolin"))
                    petDao.insertBird(Bird(2, "Claudio"))
                    petDao.insertBird(Bird(3, "Fenix"))

                    ownerDao.insertBirdAndOwnerRef(BirdsAndOwnersRef(1, 1))
                    ownerDao.insertBirdAndOwnerRef(BirdsAndOwnersRef(1, 2))
                    ownerDao.insertBirdAndOwnerRef(BirdsAndOwnersRef(2, 2))
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
