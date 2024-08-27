package nacholab.cv.i18n

interface Strings{
    val code: String
    val strings: HashMap<String, String>
    val months: Array<String>
}

private fun getStringsDB(languageCode: String): Strings {
    return when (languageCode){
        "es" -> SpanishStrings()
        else  -> EnglishStrings()
    }
}

fun String.i18n(languageCode: String): String{
    return getStringsDB(languageCode).strings[this]?:this
}

fun Int.i18nMonth(languageCode: String): String{
    return getStringsDB(languageCode).months[this]
}