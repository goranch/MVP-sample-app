package com.goranch.publicapis.data

object DbUtil {
    var strSeparator = "__,__"

    fun convertArrayToString(array: Array<String>): String {
        val str = StringBuilder()
        for (i in array.indices) {
            str.append(array[i])
            // Do not append comma at the end of last element
            if (i < array.size - 1) {
                str.append(strSeparator)
            }
        }
        return str.toString()
    }

    fun convertStringToArray(str: String): Array<String> {
        return str.split(strSeparator.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }
}
