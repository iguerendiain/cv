package nacholab.cv.pdf

import kotlin.js.json

/** Centered bold section heading, e.g. "Work Experience", "Portfolio". */
fun pdfSectionTitle(text: String): dynamic = json(
    "text" to text,
    "alignment" to "center",
    "fontSize" to PdfTheme.TITLE_FONT_SIZE,
    "bold" to true,
    "color" to PdfTheme.DEFAULT_TEXT_COLOR,
    "margin" to arrayOf(0.0, 0.0, 0.0, PdfTheme.SECTION_SPACING)
)
