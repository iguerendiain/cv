package nacholab.cv.components.resume

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaCircleChevronRight
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.window
import nacholab.cv.APP_ASSETS
import nacholab.cv.theme.CommonStyles.tint
import nacholab.cv.RESPONSIVE_WIDTH_THRESHOLD
import nacholab.cv.model.TechDataItem
import nacholab.cv.theme.CVTheme
import org.jetbrains.compose.web.css.px
import kotlin.math.ceil

@Composable
fun TechSkills(workData: List<TechDataItem>, language: String) {
    val isDesktop = window.innerWidth > RESPONSIVE_WIDTH_THRESHOLD

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(left = 4.px, right = 4.px, bottom = 10.px)
    ){
        workData.map { tech ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(top = 12.px, bottom = 12.px)
            ) {
                FaCircleChevronRight(
                    size = IconSize.X1,
                    modifier = Modifier
                        .color(CVTheme.colors.secondaryVariant)
                        .margin(right = 4.px)
                )

                SpanText(
                    text = tech.title[language]?:"",
                    modifier = Modifier
                        .fontSize(14.px)
                        .fontWeight(FontWeight.Bold)
                        .color(CVTheme.colors.secondaryVariant)
                )
            }

            val columnCount = if (isDesktop) 3 else 2
            val itemCount = tech.content.size
            val rowCount = ceil(itemCount / columnCount.toDouble()).toInt()

            for (row in 0 until rowCount) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .margin(left = 22.px, right = 22.px, bottom = 4.px)
                ) {
                    for (column in 0 until columnCount) {
                        val itemIndex = column + row * columnCount
                        if (itemIndex < itemCount){
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .padding(bottom = 8.px),
                            ){
                                Image(
                                    src = "$APP_ASSETS/listBullet.png",
                                    modifier = Modifier
                                        .margin(top = 4.px, right = 4.px)
                                        .width(9.px)
                                        .height(10.px)
                                        .tint(CVTheme.colors.secondaryHueRotationFromSepia)
                                )

                                SpanText(
                                    text = tech.content[itemIndex],
                                    modifier = Modifier
                                        .color(CVTheme.colors.primary)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}