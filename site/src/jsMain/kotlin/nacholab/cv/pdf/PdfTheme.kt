package nacholab.cv.pdf

/**
 * Layout/typography constants for the generated CV PDF.
 *
 * Default font is pdfmake's bundled Roboto (see PdfMakeInterop's vfs setup), matching the
 * Roboto family already loaded for the web page (site/build.gradle.kts).
 */
object PdfTheme {
    const val PAGE_SIZE = "A4"

    const val MARGIN_HORIZONTAL = 36.0
    const val MARGIN_TOP = 80.0
    const val MARGIN_BOTTOM = 80.0

    const val DEFAULT_FONT_SIZE = 9.0
    const val HEADER_FOOTER_FONT_SIZE = 10.0
    const val TITLE_FONT_SIZE = 12.0
    const val SUBTITLE_FONT_SIZE = 9.0

    const val SECTION_SPACING = 15.0
    const val ENTRY_SPACING = 10.0

    // Mirrors nacholab.cv.theme.MainColors.DARK_GRAY / DARK_CYAN, expressed as hex for pdfmake.
    const val DEFAULT_TEXT_COLOR = "#2A2A2A"
    const val LINK_TEXT_COLOR = "#295869"
}
