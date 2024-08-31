package nacholab.cv.tools

import nacholab.cv.i18n.i18nMonth

fun Int.toMonth(languageCode: String): String{
    return (this-1).i18nMonth(languageCode)
}