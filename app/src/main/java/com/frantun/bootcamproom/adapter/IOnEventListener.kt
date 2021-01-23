package com.frantun.bootcamproom.adapter

import com.frantun.bootcamproom.database.Word

interface IOnEventListener {
    fun onClick(word: Word)
}