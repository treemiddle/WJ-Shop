package com.jay.common

fun Int.toPrice(): String {
    val str = this.toString()
    val sb = StringBuilder(str)

    when (sb.length) {
        in 4..6 -> sb.insert(str.lastIndex - 2, ",")
        in 7..9 -> {
            sb.insert(str.lastIndex - 2, ",")
            sb.insert(str.lastIndex - 5, ",")
        }
        in 10..12 -> {
            sb.insert(str.lastIndex - 2, ",")
            sb.insert(str.lastIndex - 5, ",")
            sb.insert(str.lastIndex - 8, ",")
        }
        else -> sb
    }

    return "${sb}원"
}