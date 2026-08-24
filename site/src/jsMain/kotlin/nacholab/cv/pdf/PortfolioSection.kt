package nacholab.cv.pdf

import kotlin.js.json
import nacholab.cv.KEY_TITLE
import nacholab.cv.model.PortfolioData
import nacholab.cv.model.ProjectData

private fun projectHeaderLine(project: ProjectData, language: String): dynamic {
    val runs = mutableListOf<dynamic>()
    val titleText = project.title[language] ?: ""
    val firstUrl = project.urls?.firstOrNull()?.url

    runs.add(
        if (!firstUrl.isNullOrBlank())
            json("text" to titleText, "link" to firstUrl, "bold" to true, "color" to PdfTheme.LINK_TEXT_COLOR)
        else
            json("text" to titleText, "bold" to true, "color" to PdfTheme.DEFAULT_TEXT_COLOR)
    )

    val remainingUrls = project.urls?.drop(1).orEmpty()
    remainingUrls.forEachIndexed { index, projectUrl ->
        if (index == 0) runs.add(json("text" to " - ", "bold" to true, "color" to PdfTheme.DEFAULT_TEXT_COLOR))
        runs.add(
            json(
                "text" to (projectUrl.title[language] ?: ""),
                "link" to projectUrl.url,
                "bold" to true,
                "color" to PdfTheme.LINK_TEXT_COLOR
            )
        )
        if (index < remainingUrls.lastIndex) runs.add(json("text" to ", ", "bold" to true, "color" to PdfTheme.LINK_TEXT_COLOR))
    }

    return json("text" to runs.toTypedArray(), "fontSize" to PdfTheme.SUBTITLE_FONT_SIZE, "margin" to arrayOf(0.0, 0.0, 0.0, 4.0))
}

private fun projectItemsLine(project: ProjectData, language: String): dynamic = json(
    "text" to project.items.joinToString(" // ") { it[KEY_TITLE]?.get(language) ?: "" },
    "alignment" to "justify",
    "fontSize" to PdfTheme.DEFAULT_FONT_SIZE,
    "color" to PdfTheme.DEFAULT_TEXT_COLOR,
    "margin" to arrayOf(0.0, 0.0, 0.0, PdfTheme.SECTION_SPACING)
)

fun buildPortfolioContent(portfolioData: PortfolioData, language: String, widthPercent: Int): dynamic {
    val stackItems = mutableListOf<dynamic>()

    portfolioData.title[language]?.let { stackItems.add(pdfSectionTitle(it)) }

    portfolioData.projects.take(2).forEach { project ->
        stackItems.add(projectHeaderLine(project, language))
        stackItems.add(projectItemsLine(project, language))
    }

    return json("stack" to stackItems.toTypedArray(), "width" to "$widthPercent%")
}
