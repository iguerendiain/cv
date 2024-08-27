package nacholab.cv.components.header

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import nacholab.cv.theme.CommonStyles
import org.jetbrains.compose.web.css.px

@Composable
fun HeaderButton(text: String, onClick: () -> Unit) {
    SpanText(
        text = text.uppercase(),
        modifier = Modifier
            .onClick { onClick.invoke()}
            .fontSize(18.px)
            .fontWeight(FontWeight.Medium)
            .then(CommonStyles.colorPrimary.toModifier())
            .then(CommonStyles.hoverPointer.toModifier())
            .then(CommonStyles.hoverPrimaryVariant.toModifier())
    )
}