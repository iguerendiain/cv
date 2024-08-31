package nacholab.cv.components.footer

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowTurnUp
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.style.toModifier
import nacholab.cv.theme.CommonStyles
import nacholab.cv.theme.CVTheme
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun Footer(modifier: Modifier = Modifier, onGoToUpRequested: () -> Unit) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.px)
                .background(Color.black)
                .filter(blur(4.px))
                .zIndex(80)
                .opacity(50f.percent)
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .zIndex(90)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CVTheme.colors.background)
                    .height(56.px)
            ) {
                FaArrowTurnUp(
                    size = IconSize.X2,
                    modifier =
                    Modifier
                        .onClick { onGoToUpRequested.invoke() }
                        .then(CommonStyles.colorPrimary.toModifier())
                        .then(CommonStyles.hoverPrimaryVariant.toModifier())
                        .then(CommonStyles.hoverPointer.toModifier())
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CVTheme.colors.secondaryVariant)
                    .height(8.px)
            )
        }
    }
}