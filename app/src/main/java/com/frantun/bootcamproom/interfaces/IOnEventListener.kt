package com.frantun.bootcamproom.interfaces

import com.frantun.bootcamproom.database.Word

interface IOnEventListener {
    fun onClick(word: Word)
}