package nacholab.cv.components.contact

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.clip
import kotlinx.browser.window
import nacholab.cv.BASE_URL
import nacholab.cv.RESPONSIVE_WIDTH_THRESHOLD
import nacholab.cv.model.ContactData
import nacholab.cv.theme.CVTheme
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.px

@Composable
fun Contact(contact: ContactData) {
    val isDesktop = window.innerWidth > RESPONSIVE_WIDTH_THRESHOLD


    if (isDesktop)Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(48.px)
                .clip(Rect(cornerRadius = 18.px))
                .background(CVTheme.colors.secondaryVariant)
        ) {

            ContactLink(
                modifier = Modifier.width(300.px),
                iconSrc = "assets/ic_email.png",
                linkText = contact.email,
                linkURL = "mailto:${contact.email}"
            )

            Box(modifier = Modifier.width(230.px).height(1.px))

            ContactLink(
                modifier = Modifier.width(300.px),
                iconSrc = "assets/ic_linkedin.png",
                linkText = "LinkedIn",
                linkURL = contact.linkedin
            )
        }

        AvatarImage(BASE_URL + contact.avatar)
    } else Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ){
        AvatarImage(BASE_URL + contact.avatar)

        Box(modifier = Modifier.height(24.px))

        ContactLink(
            modifier = Modifier
                .width(300.px)
                .height(48.px)
                .clip(Rect(cornerRadius = 18.px))
                .background(CVTheme.colors.secondaryVariant),
            iconSrc = "assets/ic_email.png",
            linkText = contact.email,
            linkURL = "mailto:${contact.email}"
        )

        Box(modifier = Modifier.height(24.px))

        ContactLink(
            modifier = Modifier
                .width(300.px)
                .height(48.px)
                .clip(Rect(cornerRadius = 18.px))
                .background(CVTheme.colors.secondaryVariant),
            iconSrc = "assets/ic_linkedin.png",
            linkText = "LinkedIn",
            linkURL = contact.linkedin
        )
    }
}