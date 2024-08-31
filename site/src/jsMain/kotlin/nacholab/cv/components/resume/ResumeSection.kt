package nacholab.cv.components.resume

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.opacity
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.clip
import nacholab.cv.theme.CVTheme
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun ResumeSection(
    title: String?,
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CVTheme.colors.background)
                    .clip(Rect(cornerRadius = 12.px))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(left = 26.px, right = 26.px, top = 10.px)
                ) {
                    Box(
                        modifier = Modifier
                            .height(1.px)
                            .background(CVTheme.colors.secondary)
                            .weight(1f)
                    )
                    SpanText(
                        text = " /// $title",
                        modifier = Modifier
                            .fontSize(14.px)
                            .color(CVTheme.colors.secondary)
                    )
                }
                Box(modifier = Modifier.fillMaxWidth()) { content.invoke() }
            }
        }
    }
}