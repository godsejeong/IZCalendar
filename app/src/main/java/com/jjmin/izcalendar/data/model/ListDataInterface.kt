package com.jjmin.izcalendar.data.model

interface ListDataInterface{
    val TYPE_MAIN: Int
        get() = 1

    val TYPE_TODAY : Int
        get() = 2

    val TYPE_DETAIL: Int
        get() = 3

    fun getitem() : Int
}