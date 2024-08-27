package nacholab.cv.theme

import com.varabyte.kobweb.compose.css.CSSAngleNumericValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.filter
import com.varabyte.kobweb.compose.css.functions.hueRotate
import com.varabyte.kobweb.compose.css.functions.saturate
import com.varabyte.kobweb.compose.css.functions.sepia
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.percent

object CommonStyles {
    val hoverPointer = CssStyle{
        hover { Modifier.cursor(Cursor.Pointer) }
    }

    val hoverPrimaryVariant = CssStyle{
        hover { Modifier.color(CVTheme.colors.primaryVariant) }
    }

    val colorPrimary = CssStyle{
        base { Modifier.color(CVTheme.colors.primary) }
    }

    val colorSecondaryVariant = CssStyle{
        base { Modifier.color(CVTheme.colors.secondaryVariant) }
    }

    fun Modifier.tint(hueRotation: CSSAngleNumericValue) = styleModifier {
        filter(
            sepia(100f.percent),
            saturate(255),
            hueRotate(hueRotation)
        )
    }
}

