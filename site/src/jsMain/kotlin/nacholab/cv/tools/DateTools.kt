package nacholab.cv.tools

import nacholab.cv.i18n.i18nMonth
import kotlin.js.Date

fun Long.toMonth(languageCode: String): String{
    val date = Date(this*1000+100000000)
    return date.getMonth().i18nMonth(languageCode)
}

fun Long.toYear(): String{
    val date = Date(this*1000+100000000)
    return date.getFullYear().toString()
}