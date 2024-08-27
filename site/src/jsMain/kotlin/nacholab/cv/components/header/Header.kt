package nacholab.cv.components.header

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaAt
import com.varabyte.kobweb.silk.components.icons.fa.FaGlobe
import com.varabyte.kobweb.silk.components.icons.fa.FaTurnDown
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.window
import nacholab.cv.*
import nacholab.cv.model.NavBarData
import nacholab.cv.theme.CVTheme
import nacholab.cv.theme.CommonStyles
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

enum class HeaderEvent { LANGUAGE, RESUME, CONTACT, PORTFOLIO }

@Composable
fun Header(
    data: NavBarData,
    language: String,
    modifier: Modifier = Modifier,
    onEvent: (headerEv: HeaderEvent)-> Unit
) {
    val screenWidth = window.innerWidth
    val isDesktop = screenWidth > RESPONSIVE_WIDTH_THRESHOLD
    val mainContentWidth = (
            if (isDesktop) HORIZONTAL_CONTENT_WIDTH_DESKTOP_PERCENT
            else HORIZONTAL_CONTENT_WIDTH_PALMTOP_PERCENT
    ).percent

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CVTheme.colors.secondaryVariant)
                .height(12.px)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(CVTheme.colors.background)
                .height(56.px)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(mainContentWidth)
                    .height(56.px)
            ) {
                data.title[language]?.let {
                    SpanText(
                        text = it,
                        modifier = Modifier
                            .fontSize(22.px)
                            .fontWeight(FontWeight.Bold)
                            .color(CVTheme.colors.primary)
                    )
                }

                Box(modifier = Modifier.weight(1f)) {
                    if (isDesktop) data.menu.let { menu ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            menu.portfolio[KEY_TITLE]?.get(language)
                                ?.let { HeaderButton(it) { onEvent(HeaderEvent.PORTFOLIO) } }
                            menu.cv[KEY_TITLE]?.get(language)
                                ?.let { HeaderButton(it) { onEvent(HeaderEvent.RESUME) } }
                            menu.contact[KEY_TITLE]?.get(language)
                                ?.let { HeaderButton(it) { onEvent(HeaderEvent.CONTACT) } }
                        }
                    }
                }

                if (!isDesktop){
                    FaAt(
                        size = IconSize.X2,
                        modifier = Modifier
                            .title(data.menu.contact[KEY_TITLE]?.get(language) ?: "")
                            .then(CommonStyles.colorPrimary.toModifier())
                            .then(CommonStyles.hoverPointer.toModifier())
                            .then(CommonStyles.hoverPrimaryVariant.toModifier())
                            .onClick { onEvent(HeaderEvent.CONTACT) }
                            .margin(right = 14.px)
                    )

                    FaTurnDown(
                        size = IconSize.X2,
                        modifier = Modifier
                            .title(data.menu.cv[KEY_TITLE]?.get(language) ?: "")
                            .then(CommonStyles.colorPrimary.toModifier())
                            .then(CommonStyles.hoverPointer.toModifier())
                            .then(CommonStyles.hoverPrimaryVariant.toModifier())
                            .onClick { onEvent(HeaderEvent.RESUME) }
                            .margin(right = 14.px)
                    )
                }

                Link(path = BASE_URL_ASSETS + "iguerendiainCV.pdf"){
                    Image(
                        src = "assets/ic_download_pdf.png",
                        modifier = Modifier
                            .size(28.px)
                            .margin(right = 14.px)
                            .title(data.menu.pdf[KEY_TITLE]?.get(language) ?: "")
                    )
                }

                FaGlobe(
                    size = IconSize.X2,
                    modifier = Modifier
                        .title(data.menu.language[KEY_TITLE]?.get(language) ?: "")
                        .then(CommonStyles.colorPrimary.toModifier())
                        .then(CommonStyles.hoverPointer.toModifier())
                        .then(CommonStyles.hoverPrimaryVariant.toModifier())
                        .onClick { onEvent(HeaderEvent.LANGUAGE) }
                )
            }
        }

    }
}