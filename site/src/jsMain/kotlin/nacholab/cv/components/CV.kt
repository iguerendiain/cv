package nacholab.cv.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.functions.url
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.window
import nacholab.cv.APP_ASSETS
import nacholab.cv.DEFAULT_LANGUAGE
import nacholab.cv.components.common.SectionTitle
import nacholab.cv.components.contact.Contact
import nacholab.cv.components.footer.Footer
import nacholab.cv.components.header.Header
import nacholab.cv.components.header.HeaderEvent
import nacholab.cv.components.portfolio.Portfolio
import nacholab.cv.components.resume.Resume
import nacholab.cv.model.MainCV
import nacholab.cv.theme.CVTheme
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.w3c.dom.HTMLElement

val baseStyle = CssStyle {
    base{
        Modifier
            .fontFamily("Roboto")
    }
}

/**
 *  The screenWidth argument is only passed so the component will
 *  recompose when it changes. That way, each child component can
 *  simply read the size of the screen from window.innerWidth instead
 *  of passing the value down the tree.
 */
@Composable
fun CV(cvData: MainCV, screenWidth: Int){
    var resumeSectionRef by remember { mutableStateOf<HTMLElement?>(null) }
    var contactSectionRef by remember { mutableStateOf<HTMLElement?>(null) }
    var languageDialogPresent by remember { mutableStateOf(false) }
    var language by remember { mutableStateOf(
        window
            .navigator
            .language
            .split("-")
            .firstOrNull()
            ?.lowercase()
            ?.let { parsedLanguage ->
                if (cvData.languages.find { it.code == parsedLanguage } != null) parsedLanguage
                else cvData.defaultLanguage
            }
            ?:DEFAULT_LANGUAGE
    )}

    Box(modifier = baseStyle.toModifier().then(Modifier.fillMaxSize())) {

        Header(
            data = cvData.navbar,
            language = language,
            modifier = Modifier
                .fillMaxWidth()
                .position(Position.Fixed)
                .zIndex(99)
        ) {
            when (it) {
                HeaderEvent.LANGUAGE -> languageDialogPresent = !languageDialogPresent
                HeaderEvent.RESUME -> window.scrollTo(
                    x = 0.0,
                    y = (resumeSectionRef?.offsetTop?.toDouble() ?: 0.0) - 100.0
                )

                HeaderEvent.CONTACT -> window.scrollTo(
                    x = 0.0,
                    y = (contactSectionRef?.offsetTop?.toDouble() ?: 0.0) - 100.0
                )

                HeaderEvent.PORTFOLIO -> window.scrollTo(x = 0.0, y = 0.0)
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(CVTheme.colors.background)
                .backgroundImage(url("$APP_ASSETS/bg.png"))
                .overflow(Overflow.Auto)
        ) {
            Box(modifier = Modifier.height(106.px))
            cvData.portfolio.title[language]?.uppercase()?.let { SectionTitle(it) }
            Box(modifier = Modifier.height(36.px).fillMaxWidth())
            Portfolio(cvData.portfolio.projects, language)
            Box(modifier = Modifier.height(36.px).fillMaxWidth())
            cvData.cv.title[language]?.uppercase()?.let {
                SectionTitle(
                    title = it,
                    ref = ref { elm -> resumeSectionRef = elm }
                )
            }
            Box(modifier = Modifier.height(36.px).fillMaxWidth())
            Resume(cvData.cv, language)
            Box(modifier = Modifier.height(36.px).fillMaxWidth())
            cvData.contact.title[language]?.uppercase()?.let {
                SectionTitle(
                    title = it,
                    ref = ref { elm -> contactSectionRef = elm }
                )
            }
            Box(modifier = Modifier.height(36.px).fillMaxWidth())
            Contact(cvData.contact)
            Box(modifier = Modifier.height(36.px).fillMaxWidth())
            Footer(modifier = Modifier.fillMaxWidth()) {
                window.scrollTo(
                    x = 0.0,
                    y = 0.0
                )
            }
        }

        if (languageDialogPresent) LanguageSelectorDialog(language, cvData.languages) {
            language = it
            languageDialogPresent = false
        }
    }
}
