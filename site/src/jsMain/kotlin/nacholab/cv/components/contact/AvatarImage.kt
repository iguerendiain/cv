package nacholab.cv.components.contact

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.clip
import nacholab.cv.theme.CVTheme
import org.jetbrains.compose.web.css.px

@Composable
fun AvatarImage(imageSrc: String){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(CVTheme.colors.secondaryVariant)
            .size(230.px)
            .clip(Circle())
    ){
        Image(
            src = imageSrc,
            modifier = Modifier
                .size(200.px)
                .clip(Circle())
        )
    }
}