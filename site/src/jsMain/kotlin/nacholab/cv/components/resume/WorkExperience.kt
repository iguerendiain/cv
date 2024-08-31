package nacholab.cv.components.resume

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaCircleChevronRight
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.window
import nacholab.cv.APP_ASSETS
import nacholab.cv.theme.CommonStyles
import nacholab.cv.theme.CommonStyles.tint
import nacholab.cv.RESPONSIVE_WIDTH_THRESHOLD
import nacholab.cv.i18n.i18n
import nacholab.cv.model.WorkExperienceDataItem
import nacholab.cv.theme.CVTheme
import nacholab.cv.tools.toMonth
import org.jetbrains.compose.web.css.px

@Composable
fun WorkExperience(
    workData: List<WorkExperienceDataItem>,
    language: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(left = 4.px, right = 4.px)
    ){
        workData.map { job ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(top = 12.px, bottom = 8.px)
            ){
                FaCircleChevronRight(
                    size = IconSize.X1,
                    modifier = Modifier
                        .color(CVTheme.colors.secondaryVariant)
                        .margin(right = 4.px)
                )

                job.title[language]?.let {
                    job.url?.let { url ->
                        Link(
                            path = url,
                            text = it,
                            modifier = Modifier
                                .fontSize(14.px)
                                .fontWeight(FontWeight.Bold)
                                .then(CommonStyles.colorSecondaryVariant.toModifier())
                                .then(CommonStyles.hoverPrimaryVariant.toModifier())
                                .then(CommonStyles.hoverPrimaryVariant.toModifier())
                        )
                    }?: SpanText(
                        text = it,
                        modifier = Modifier
                            .fontSize(14.px)
                            .fontWeight(FontWeight.Bold)
                            .color(CVTheme.colors.secondaryVariant)
                    )
                }
                if (window.innerWidth > RESPONSIVE_WIDTH_THRESHOLD) {
                    SpanText(
                        text = " // ${if (job.to==null) "${"since".i18n(language)} " else ""}${job.from[0].toMonth(language)} ${job.from[1]}",
                        modifier = Modifier
                            .fontSize(14.px)
                            .color(CVTheme.colors.secondaryVariant)
                            .fontWeight(FontWeight.Bold)
                    )

                    job.to?.let {
                        SpanText(
                            text = " ~ ${job.to[0].toMonth(language)} ${job.to[1]}",
                            modifier = Modifier
                                .fontSize(14.px)
                                .color(CVTheme.colors.secondaryVariant)
                                .fontWeight(FontWeight.Bold)
                        )
                    }
                }
            }

            if (window.innerWidth <= RESPONSIVE_WIDTH_THRESHOLD){
                Row(
                    modifier = Modifier
                        .padding(left = 20.px, top = 4.px, bottom = 8.px)
                ) {
                    SpanText(
                        text = "${if (job.to==null) "${"since".i18n(language)} " else ""}${job.from[0].toMonth(language)} ${job.from[1]}",
                        modifier = Modifier
                            .fontSize(14.px)
                            .color(CVTheme.colors.secondaryVariant)
                            .fontWeight(FontWeight.Bold)
                    )

                    job.to?.let {
                        SpanText(
                            text = " ~ ${job.to[0].toMonth(language)} ${job.to[1]}",
                            modifier = Modifier
                                .fontSize(14.px)
                                .color(CVTheme.colors.secondaryVariant)
                                .fontWeight(FontWeight.Bold)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(left = 22.px, right = 22.px, bottom = 20.px)
            ){
                Image(
                    src = "$APP_ASSETS/listBullet.png",
                    modifier = Modifier
                        .margin(top = 4.px)
                        .width(9.px)
                        .height(10.px)
                        .tint(CVTheme.colors.secondaryHueRotationFromSepia)
                )

                job.description[language]?.let {
                    SpanText(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .margin(left = 8.px)
                            .textAlign(TextAlign.Justify)
                            .fontSize(14.px)
                            .lineHeight(22.px)
                            .color(CVTheme.colors.primary)
                    )
                }
            }
        }
    }
}