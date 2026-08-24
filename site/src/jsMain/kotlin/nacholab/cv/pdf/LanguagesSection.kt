package nacholab.cv.pdf

import kotlin.js.json
import nacholab.cv.model.LanguagesData

fun buildLanguagesContent(languagesData: LanguagesData, language: String): dynamic {
    val stackItems = mutableListOf<dynamic>()

    languagesData.title[language]?.let { stackItems.add(pdfSectionTitle(it)) }

    languagesData.content.forEach { lang ->
        stackItems.add(
            json(
                "text" to arrayOf(
                    json(
                        "text" to "${lang.title[language]}: ",
                        "bold" to true,
                        "fontSize" to PdfTheme.SUBTITLE_FONT_SIZE,
                        "color" to PdfTheme.DEFAULT_TEXT_COLOR
                    ),
                    json(
                        "text" to (lang.description[language] ?: ""),
                        "fontSize" to PdfTheme.DEFAULT_FONT_SIZE,
                        "color" to PdfTheme.DEFAULT_TEXT_COLOR
                    )
                ),
                "margin" to arrayOf(0.0, 0.0, 0.0, PdfTheme.SECTION_SPACING)
            )
        )
    }

    return json("stack" to stackItems.toTypedArray(), "margin" to arrayOf(0.0, 0.0, 0.0, PdfTheme.SECTION_SPACING))
}
