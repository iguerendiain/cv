package nacholab.cv.pdf

import kotlin.js.json
import nacholab.cv.model.MainCV

private fun buildCvDocDefinition(cvData: MainCV, language: String): dynamic = json(
    "pageSize" to PdfTheme.PAGE_SIZE,
    "pageMargins" to arrayOf(PdfTheme.MARGIN_HORIZONTAL, PdfTheme.MARGIN_TOP, PdfTheme.MARGIN_HORIZONTAL, PdfTheme.MARGIN_BOTTOM),
    "header" to { _: Int, _: Int, _: dynamic -> buildHeaderFooterContent(cvData.navbar, cvData.contact, language) },
    "footer" to { _: Int, _: Int -> buildHeaderFooterContent(cvData.navbar, cvData.contact, language) },
    "content" to arrayOf(
        buildWorkExperienceContent(cvData.cv.work, language),
        buildTechnicalSkillsContent(cvData.cv.tech, language),
        buildLanguagesContent(cvData.cv.languages, language),
        buildPortfolioContent(cvData.portfolio, language)
    ),
    "defaultStyle" to json("font" to "Roboto"),
    "info" to json("title" to (cvData.navbar.title[language] ?: "CV"))
)

fun downloadCvPdf(cvData: MainCV, language: String, filename: String = "iguerendiainCV.pdf") {
    downloadPdf(buildCvDocDefinition(cvData, language), filename)
}

// TEMP: manual verification helper, removed once end-to-end testing is done.
fun debugBuildDocDefinition(cvData: MainCV, language: String): dynamic = buildCvDocDefinition(cvData, language)
