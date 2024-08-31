package nacholab.cv.components.common

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.dom.ElementRefScope
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.text.SpanText
import nacholab.cv.theme.CVTheme
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.w3c.dom.HTMLElement

@Composable
fun SectionTitle(title: String, ref: ElementRefScope<HTMLElement>? = null){
    Box(
        ref = ref,
        modifier = Modifier.fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(43.px)
                .background(Color.black)
                .filter(blur(4.px))
                .zIndex(80)
                .opacity(50f.percent)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.px)
                .background(CVTheme.colors.background)
                .zIndex(90)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.px)
                    .background(CVTheme.colors.secondary)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.px)

            ) {
                SpanText(
                    text = title,
                    modifier = Modifier
                        .color(CVTheme.colors.secondary)
                        .fontSize(18.px)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.px)
                    .background(CVTheme.colors.secondary)
            )
        }
    }
}