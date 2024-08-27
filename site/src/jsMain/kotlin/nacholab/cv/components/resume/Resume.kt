package nacholab.cv.components.resume

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import kotlinx.browser.window
import nacholab.cv.HORIZONTAL_CONTENT_WIDTH_DESKTOP_PERCENT
import nacholab.cv.HORIZONTAL_CONTENT_WIDTH_PALMTOP_PERCENT
import nacholab.cv.RESPONSIVE_WIDTH_THRESHOLD
import nacholab.cv.model.ResumeData
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun Resume(cv: ResumeData, language: String) {
    val screenWidth = window.innerWidth
    val isDesktop = screenWidth > RESPONSIVE_WIDTH_THRESHOLD
    val contentWidth =
        (if (isDesktop) HORIZONTAL_CONTENT_WIDTH_DESKTOP_PERCENT
        else HORIZONTAL_CONTENT_WIDTH_PALMTOP_PERCENT).percent

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(contentWidth)
    ){
        ResumeSection(cv.work.title[language]){
            WorkExperience(cv.work.content, language)
        }
        Box(modifier = Modifier.height(32.px))
        ResumeSection(cv.tech.title[language]){
            TechSkills(cv.tech.content, language)
        }
        Box(modifier = Modifier.height(32.px))
        ResumeSection(cv.languages.title[language]){
            Languages(cv.languages.content, language)
        }
        Box(modifier = Modifier.height(32.px))
    }
}