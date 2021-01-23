package com.frantun.bootcamproom.application

import android.app.Application
import com.frantun.bootcamproom.database.BootcampDatabase
import com.frantun.bootcamproom.repositories.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {

    val applicationScope = CoroutineScope((SupervisorJob()))

    val database by lazy { BootcampDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}