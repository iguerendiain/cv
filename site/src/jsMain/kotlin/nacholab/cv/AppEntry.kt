package nacholab.cv

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.window
import nacholab.cv.model.*
import nacholab.cv.pdf.debugBuildDocDefinition
import nacholab.cv.pdf.pdfDataUrl
import org.jetbrains.compose.web.css.*

// TEMP: sample data + devtools hook to verify the PDF pipeline while db.json is mid-edit
// and fails to parse. Removed once end-to-end testing is done.
private fun sampleCv(): MainCV = MainCV(
    languages = listOf(
        Language("en", hashMapOf("en" to "English", "es" to "Inglés")),
        Language("es", hashMapOf("en" to "Spanish", "es" to "Español"))
    ),
    defaultLanguage = "en",
    navbar = NavBarData(
        title = hashMapOf("en" to "Ignacio Guerendiain", "es" to "Ignacio Guerendiain"),
        menu = NavBarMenuData(
            portfolio = hashMapOf("title" to hashMapOf("en" to "Portfolio", "es" to "Portafolio")),
            cv = hashMapOf("title" to hashMapOf("en" to "Resume", "es" to "Currículum")),
            contact = hashMapOf("title" to hashMapOf("en" to "Contact", "es" to "Contacto")),
            pdf = hashMapOf("title" to hashMapOf("en" to "Download PDF", "es" to "Descargar PDF")),
            language = hashMapOf("title" to hashMapOf("en" to "Language", "es" to "Idioma"))
        )
    ),
    portfolio = PortfolioData(
        title = hashMapOf("en" to "Portfolio", "es" to "Portafolio"),
        projects = listOf(
            ProjectData(
                title = hashMapOf("en" to "Sample App", "es" to "App de Ejemplo"),
                icon = "assets/portfolio/aa.jpg",
                items = listOf(
                    hashMapOf("title" to hashMapOf("en" to "Feature one", "es" to "Función uno")),
                    hashMapOf("title" to hashMapOf("en" to "Feature two", "es" to "Función dos"))
                ),
                urls = listOf(
                    ProjectURLData("assets/googlePlay.png", hashMapOf("en" to "Play Store", "es" to "Play Store"), "https://play.google.com/example"),
                    ProjectURLData("assets/www.png", hashMapOf("en" to "Website", "es" to "Sitio web"), "https://example.com")
                )
            ),
            ProjectData(
                title = hashMapOf("en" to "Second App", "es" to "Segunda App"),
                icon = "assets/portfolio/angrywords.png",
                items = listOf(hashMapOf("title" to hashMapOf("en" to "Only feature", "es" to "Única función"))),
                urls = null
            )
        )
    ),
    cv = ResumeData(
        title = hashMapOf("en" to "Resume", "es" to "Currículum"),
        work = WorkExperienceData(
            title = hashMapOf("en" to "Work Experience", "es" to "Experiencia Laboral"),
            content = listOf(
                WorkExperienceDataItem(
                    title = hashMapOf("en" to "Senior Engineer @ Example Corp", "es" to "Ingeniero Senior @ Example Corp"),
                    from = listOf(3, 2020),
                    to = null,
                    description = hashMapOf(
                        "en" to listOf("Leading development of X.", "Also did Y and Z."),
                        "es" to listOf("Liderando el desarrollo de X.", "También hice Y y Z.")
                    ),
                    url = "https://example-corp.com"
                ),
                WorkExperienceDataItem(
                    title = hashMapOf("en" to "Engineer @ Old Corp", "es" to "Ingeniero @ Old Corp"),
                    from = listOf(1, 2018),
                    to = listOf(2, 2020),
                    description = hashMapOf(
                        "en" to listOf("Built things."),
                        "es" to listOf("Construí cosas.")
                    ),
                    url = null
                )
            )
        ),
        tech = TechData(
            title = hashMapOf("en" to "Technical Skills", "es" to "Habilidades Técnicas"),
            content = listOf(
                TechDataItem(hashMapOf("en" to "Languages", "es" to "Lenguajes"), listOf("Kotlin", "TypeScript", "Python")),
                TechDataItem(hashMapOf("en" to "Tools", "es" to "Herramientas"), listOf("Git", "Docker"))
            )
        ),
        languages = LanguagesData(
            title = hashMapOf("en" to "Languages", "es" to "Idiomas"),
            content = listOf(
                LanguageDataItem(hashMapOf("en" to "Spanish", "es" to "Español"), hashMapOf("en" to "Native", "es" to "Nativo")),
                LanguageDataItem(hashMapOf("en" to "English", "es" to "Inglés"), hashMapOf("en" to "Fluent", "es" to "Fluido"))
            )
        )
    ),
    contact = ContactData(
        title = hashMapOf("en" to "Contact", "es" to "Contacto"),
        avatar = "assets/avatar.png",
        linkedin = "https://www.linkedin.com/in/example",
        email = "test@example.com"
    )
)

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    window.asDynamic().testCvPdf = { lang: String ->
        pdfDataUrl(debugBuildDocDefinition(sampleCv(), lang)) { dataUrl ->
            window.asDynamic().__pdfTestResult = dataUrl
        }
    }

    SilkApp {
        Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
            content()
        }
    }
}
