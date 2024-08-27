package nacholab.cv.components.contact

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import nacholab.cv.theme.CommonStyles
import org.jetbrains.compose.web.css.px

@Composable
fun ContactLink(modifier: Modifier = Modifier, iconSrc: String, linkText: String, linkURL: String){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        Image(
            src = iconSrc,
            modifier = Modifier
                .margin(right = 4.px)
                .size(24.px)
        )

        Link(
            path = linkURL,
            text = linkText,
            modifier = Modifier
                .fontSize(16.px)
                .fontWeight(FontWeight.Medium)
                .then(CommonStyles.colorPrimary.toModifier())
                .then(CommonStyles.hoverPrimaryVariant.toModifier())
        )
    }
}