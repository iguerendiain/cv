package nacholab.cv.tools

import com.varabyte.kobweb.compose.ui.graphics.Color

fun Color.withAlpha(alpha: Int): Color{
    val rgb = this.toRgb()

    return Color.argb(
        a = alpha,
        r = rgb.red,
        g = rgb.green,
        b = rgb.blue
    )
}