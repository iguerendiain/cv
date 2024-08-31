package nacholab.cv.components.portfolio

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.aspectRatio
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.width
import kotlinx.browser.window
import nacholab.cv.HORIZONTAL_CONTENT_WIDTH_DESKTOP_PERCENT
import nacholab.cv.HORIZONTAL_CONTENT_WIDTH_PALMTOP_PERCENT
import nacholab.cv.RESPONSIVE_WIDTH_THRESHOLD
import nacholab.cv.model.ProjectData
import org.jetbrains.compose.web.css.px
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun Portfolio(
    projects: List<ProjectData>,
    language: String
){
    val projectCount = projects.size
    val projectItemWidth: Int
    val columnCount: Int
    val rowCount: Int
    val screenWidth = window.innerWidth
    val isDesktop = screenWidth > RESPONSIVE_WIDTH_THRESHOLD
    val contentWidthPercent = (
            if (isDesktop) HORIZONTAL_CONTENT_WIDTH_DESKTOP_PERCENT
            else HORIZONTAL_CONTENT_WIDTH_PALMTOP_PERCENT
    )
    val contentWidthPercentFactor = contentWidthPercent / 100f
    val projectWidth = 350

    if (isDesktop){
        projectItemWidth = projectWidth
        columnCount = floor((screenWidth * contentWidthPercentFactor) / projectWidth).toInt()
        rowCount = ceil(projectCount / columnCount.toDouble()).toInt()
    }else{
        projectItemWidth = ceil(screenWidth * contentWidthPercent).toInt()
        columnCount = 1
        rowCount = projectCount
    }

    for (row in 0 until rowCount){
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            for (column in 0 until columnCount) {
                val projectIndex = column + row * columnCount

                if (projectIndex < projectCount) PortfolioItem(
                    project = projects[projectIndex],
                    language = language,
                    modifier = Modifier
                        .width(projectItemWidth.px)
                        .then(if (isDesktop) Modifier.aspectRatio(1f) else Modifier)
//                        .margin(all = 10.px)
                )
            }
        }
    }
}