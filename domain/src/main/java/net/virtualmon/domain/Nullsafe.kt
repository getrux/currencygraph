package net.virtualmon.domain

fun String?.forceNotEmpty() : String = this ?: ""

fun Int?.forceNotEmpty() : Int = this ?: -1