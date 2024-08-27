package nacholab.cv.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.clip
import nacholab.cv.theme.CommonStyles
import nacholab.cv.model.Language
import nacholab.cv.theme.CVTheme
import nacholab.cv.tools.withAlpha
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun LanguageSelectorDialog(
    currentLanguage: String,
    languages: List<Language>,
    onSelectedLanguage: (language: String)-> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(CVTheme.colors.background.withAlpha(200))
            .position(Position.Fixed)
            .top(0.px)
            .left(0.px)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(all = 2.px)
                .clip(Rect(cornerRadius = 16.px))
                .background(CVTheme.colors.secondary)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(300.px)
                    .clip(Rect(cornerRadius = 16.px))
                    .background(CVTheme.colors.background)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 24.px)
                ) {
                    languages.mapIndexed { index, language ->
                        SpanText(
                            text = language.name[currentLanguage] ?: "",
                            modifier = Modifier
                                .fontSize(20.px)
                                .fillMaxWidth(80.percent)
                                .padding(top = 8.px, bottom = 8.px)
                                .textAlign(TextAlign.Center)
                                .fontWeight(FontWeight.Bold)
                                .clip(Rect(cornerRadius = 8.px))
                                .background(CVTheme.colors.secondaryVariant)
                                .then(CommonStyles.colorPrimary.toModifier())
                                .then(CommonStyles.hoverPrimaryVariant.toModifier())
                                .then(CommonStyles.hoverPointer.toModifier())
                                .onClick { onSelectedLanguage(language.code) }
                        )

                        if (index < languages.size - 1) Box(modifier = Modifier.height(12.px))
                    }
                }
            }
        }
    }
}