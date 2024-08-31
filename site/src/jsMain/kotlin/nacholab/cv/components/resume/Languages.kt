package nacholab.cv.components.resume

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.icons.fa.FaCircleChevronRight
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.text.SpanText
import nacholab.cv.model.LanguageDataItem
import nacholab.cv.theme.CVTheme
import org.jetbrains.compose.web.css.px

@Composable
fun Languages(languages: List<LanguageDataItem>, language: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(left = 4.px, right = 4.px, bottom = 10.px)
    ){
        languages.map { lang ->
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
                    text = lang.title[language]?:"",
                    modifier = Modifier
                        .fontSize(14.px)
                        .fontWeight(FontWeight.Bold)
                        .color(CVTheme.colors.secondaryVariant)
                )
            }

            lang.description[language]?.let {
                SpanText(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(left = 20.px, right = 20.px, bottom = 20.px)
                        .textAlign(TextAlign.Justify)
                        .fontSize(18.px)
                        .lineHeight(22.px)
                        .color(CVTheme.colors.primary)
                )
            }
        }
    }
}