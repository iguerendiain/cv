package nacholab.cv.pdf

import kotlin.js.json
import nacholab.cv.model.TechData

fun buildTechnicalSkillsContent(techData: TechData, language: String): dynamic {
    val stackItems = mutableListOf<dynamic>()

    techData.title[language]?.let { stackItems.add(pdfSectionTitle(it)) }

    techData.content.forEach { skill ->
        stackItems.add(
            json(
                "text" to arrayOf(
                    json(
                        "text" to "${skill.title[language]}: ",
                        "bold" to true,
                        "fontSize" to PdfTheme.SUBTITLE_FONT_SIZE,
                        "color" to PdfTheme.DEFAULT_TEXT_COLOR
                    ),
                    json(
                        "text" to skill.content.joinToString(", "),
                        "fontSize" to PdfTheme.DEFAULT_FONT_SIZE,
                        "color" to PdfTheme.DEFAULT_TEXT_COLOR
                    )
                ),
                "margin" to arrayOf(0.0, 0.0, 0.0, PdfTheme.ENTRY_SPACING)
            )
        )
    }

    return json("stack" to stackItems.toTypedArray(), "margin" to arrayOf(0.0, 0.0, 0.0, PdfTheme.SECTION_SPACING))
}
