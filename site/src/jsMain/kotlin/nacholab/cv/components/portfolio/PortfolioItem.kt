package nacholab.cv.components.portfolio

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.CSSTextShadow
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.opacity
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.clip
import nacholab.cv.APP_ASSETS
import nacholab.cv.BASE_URL
import nacholab.cv.theme.CommonStyles.tint
import nacholab.cv.KEY_TITLE
import nacholab.cv.model.ProjectData
import nacholab.cv.theme.CVTheme
import nacholab.cv.tools.withAlpha
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import kotlin.math.ceil

@Composable
fun PortfolioItem(
    project: ProjectData,
    language: String,
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    left = 8.px,
                    top = 8.px,
                    right = 3.px,
                    bottom = 3.px
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.black)
                    .filter(blur(3.px), opacity(50f.percent))
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.px)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CVTheme.colors.background)
                    .clip(Rect(cornerRadius = 10.px))
            ) {
                Image(
                    src = BASE_URL + project.icon,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.px)
                        .objectFit(ObjectFit.Cover)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.px)
                        .background(CVTheme.colors.background.withAlpha(128))
                )

                Column(modifier = Modifier.fillMaxSize()) {
                    Row {
                        Image(
                            src = BASE_URL + project.icon,
                            modifier = Modifier
                                .margin(all = 10.px)
                                .height(52.px)
                                .width(52.px)
                        )

                        SpanText(
                            text = project.title[language] ?: "",
                            modifier = Modifier
                                .fontWeight(FontWeight.Medium)
                                .color(CVTheme.colors.primary)
                                .fontSize(18.px)
                                .margin(top = 24.px)
                                .textShadow(
                                    CSSTextShadow(
                                        offsetX = 2.px,
                                        offsetY = 2.px,
                                        blurRadius = 2.px,
                                        color = CVTheme.colors.background
                                    )
                                )
                        )
                    }

                    val columnCount = 2
                    val projectItemCount = project.items.size
                    val rowCount = ceil(projectItemCount / columnCount.toDouble()).toInt()

                    Box(modifier = Modifier.height(10.px))

                    for (row in 0 until rowCount) {
                        Row(
                            modifier = Modifier
                                .padding(left = 10.px, right = 10.px, bottom = 5.px)
                                .fillMaxWidth()
                        ) {
                            for (column in 0 until columnCount) {
                                val projectItemIndex = column + row * columnCount
                                if (projectItemIndex < projectItemCount)
                                    Row(
                                        modifier = Modifier.fillMaxWidth(((1f / columnCount) * 100).percent),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Image(
                                            src = "$APP_ASSETS/listBullet.png",
                                            modifier = Modifier
                                                .width(9.px)
                                                .height(10.px)
                                                .margin(top = 2.px)
                                                .tint(CVTheme.colors.secondaryHueRotationFromSepia)
                                        )

                                        SpanText(
                                            text = project.items[projectItemIndex][KEY_TITLE]?.get(language) ?: "",
                                            modifier = Modifier
                                                .margin(left = 4.px, right = 10.px)
                                                .fontSize(14.px)
                                                .lineHeight(20.px)
                                                .color(CVTheme.colors.primary)
                                        )
                                    }
                            }
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(left = 10.px, right = 10.px),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(1.px)
                                        .background(CVTheme.colors.secondary)
                                )

                                SpanText(
                                    text = "///",
                                    modifier = Modifier
                                        .color(CVTheme.colors.secondary)
                                        .fontSize(18.px)
                                        .fontWeight(FontWeight.Light)
                                        .margin(left = 8.px, right = 8.px)
                                )

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(1.px)
                                        .background(CVTheme.colors.secondary)
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.px, bottom = 12.px)
                                    .height(56.px),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                project.urls?.map { projectUrl ->
                                    Anchor(
                                        href = projectUrl.url,
                                        attrs = { attr("target", "_blank") }
                                    ) {
                                        Image(
                                            src = BASE_URL + projectUrl.icon,
                                            modifier = Modifier
                                                .margin(all = 4.px)
                                                .size(24.px)
                                        )
                                    }
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.px)
                                    .background(CVTheme.colors.secondary)
                            )
                        }
                    }
                }
            }
        }
    }
}