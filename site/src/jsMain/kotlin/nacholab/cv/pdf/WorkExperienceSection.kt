package nacholab.cv.pdf

import kotlin.js.json
import nacholab.cv.i18n.i18n
import nacholab.cv.model.WorkExperienceData
import nacholab.cv.model.WorkExperienceDataItem
import nacholab.cv.tools.toMonth

private fun dateRangeText(from: List<Int>, to: List<Int>?, language: String): String =
    if (to != null) "${from[0].toMonth(language)} ${from[1]} ~ ${to[0].toMonth(language)} ${to[1]}"
    else "${"since".i18n(language)} ${from[0].toMonth(language)} ${from[1]}"

private fun jobTitleContent(job: WorkExperienceDataItem, name: String, language: String): dynamic {
    val nameRun = if (!job.url.isNullOrBlank())
        json("text" to name, "link" to job.url, "color" to PdfTheme.LINK_TEXT_COLOR)
    else
        json("text" to name, "color" to PdfTheme.DEFAULT_TEXT_COLOR)

    return json(
        "text" to arrayOf(
            nameRun,
            json("text" to " // ", "color" to PdfTheme.DEFAULT_TEXT_COLOR),
            json("text" to dateRangeText(job.from, job.to, language), "color" to PdfTheme.DEFAULT_TEXT_COLOR)
        ),
        "fontSize" to PdfTheme.DEFAULT_FONT_SIZE,
        "margin" to arrayOf(0.0, 0.0, 0.0, 4.0)
    )
}

fun buildWorkExperienceContent(workData: WorkExperienceData, language: String): dynamic {
    val stackItems = mutableListOf<dynamic>()

    workData.title[language]?.let { stackItems.add(pdfSectionTitle(it)) }

    workData.content.forEach { job ->
        val jobItems = mutableListOf<dynamic>()

        job.title[language]?.let { jobItems.add(jobTitleContent(job, it, language)) }

        val paragraphs = job.description[language].orEmpty()
        if (paragraphs.isNotEmpty()) {
            jobItems.add(
                json(
                    "ul" to paragraphs.map { paragraph ->
                        json(
                            "text" to paragraph,
                            "alignment" to "justify",
                            "fontSize" to PdfTheme.DEFAULT_FONT_SIZE,
                            "color" to PdfTheme.DEFAULT_TEXT_COLOR
                        )
                    }.toTypedArray()
                )
            )
        }

        // Keep each job entry (title + bullets) together, never split across a page break.
        stackItems.add(
            json(
                "stack" to jobItems.toTypedArray(),
                "unbreakable" to true,
                "margin" to arrayOf(0.0, 0.0, 0.0, PdfTheme.SECTION_SPACING)
            )
        )
    }

    return json("stack" to stackItems.toTypedArray(), "margin" to arrayOf(0.0, 0.0, 0.0, PdfTheme.SECTION_SPACING))
}
