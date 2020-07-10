package com.gosemathraj.foodzilla.data.models

import java.util.regex.Matcher
import java.util.regex.Pattern

class Food {
    var id : Int? = null
    var image : String? = null
    var filter : String? = null
    var title : String? = null
    var isFavourite : Boolean = false

    fun getTagList() : String {
        val regex = ":[\\s\\S]*?]"
        val r: Pattern = Pattern.compile(regex)
        var tags = ""
        if (!this.filter.isNullOrEmpty()) {
            val tagsStringArray = this.filter?.split(",")
            if (tagsStringArray != null) {
                for (item in tagsStringArray) {
                    val m: Matcher = r.matcher(item)
                    if (m.find()) {
                        val tag = m.group()
                        tags = tags + " , " + tag.substring(1, tag.length - 3)
                    }
                }
            }
        }
        if (tags.isNullOrEmpty()) {
            return "Tags not present"
        }
        return "Tags : ${tags.replaceFirst(",","")}"
    }
}