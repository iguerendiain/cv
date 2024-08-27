package nacholab.cv.theme

import com.varabyte.kobweb.compose.css.CSSAngleNumericValue
import com.varabyte.kobweb.compose.ui.graphics.Color

data class ColorScheme(
    val primary: Color,
    val primaryVariant: Color,
    val secondary: Color,
    val secondaryVariant: Color,
    val background: Color,

    val secondaryHueRotationFromSepia: CSSAngleNumericValue
)