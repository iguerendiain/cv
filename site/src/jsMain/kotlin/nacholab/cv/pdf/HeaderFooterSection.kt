package nacholab.cv.pdf

import kotlin.js.json
import nacholab.cv.model.ContactData
import nacholab.cv.model.NavBarData

private const val PHONE_NUMBER = "+54 11 3130 1701"
private const val PHONE_URL = "tel:+541131301701"
private const val PORTFOLIO_URL = "https://ignacio.guerendiain.com.ar"

private fun textRun(text: String, link: String? = null): dynamic =
    if (link != null) json("text" to text, "link" to link, "color" to PdfTheme.LINK_TEXT_COLOR)
    else json("text" to text)

/**
 * Centered two-line block (title/phone/email, then portfolio/LinkedIn links) repeated on every
 * page as both the pdfmake `header` and `footer`.
 */
fun buildHeaderFooterContent(navBarData: NavBarData, contactData: ContactData, language: String): dynamic {
    val title = navBarData.title[language] ?: ""

    return json(
        "margin" to arrayOf(PdfTheme.MARGIN_HORIZONTAL, 20.0, PdfTheme.MARGIN_HORIZONTAL, 0.0),
        "fontSize" to PdfTheme.HEADER_FOOTER_FONT_SIZE,
        "color" to PdfTheme.DEFAULT_TEXT_COLOR,
        "stack" to arrayOf(
            json(
                "text" to arrayOf(
                    textRun("$title // "),
                    textRun(PHONE_NUMBER, PHONE_URL),
                    textRun(" // "),
                    textRun(contactData.email, "mailto:${contactData.email}")
                ),
                "alignment" to "center"
            ),
            json(
                "text" to arrayOf(
                    textRun(PORTFOLIO_URL, PORTFOLIO_URL),
                    textRun(" // "),
                    textRun(contactData.linkedin, contactData.linkedin)
                ),
                "alignment" to "center",
                "margin" to arrayOf(0.0, 2.0, 0.0, 0.0)
            )
        )
    )
}
