package nacholab.cv.components.resume

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.text.SpanText
import nacholab.cv.theme.CVTheme
import org.jetbrains.compose.web.css.px

@Composable
fun ResumeSection(
    title: String?,
    content: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(CVTheme.colors.background)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(left = 26.px, right = 26.px, top = 10.px)
        ){
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