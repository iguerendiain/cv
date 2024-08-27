package nacholab.cv.theme

object CVTheme {

    private val lightColors = ColorScheme(
        primary = MainColors.WHITE,
        primaryVariant = MainColors.LIGHT_CYAN,
        secondary = MainColors.GREEN,
        secondaryVariant = MainColors.DARK_CYAN,
        background = MainColors.DARK_GRAY,
        secondaryHueRotationFromSepia = MainColors.HUE_ROTATION_FROM_SEPIA_GREEEN
    )

    val colors = lightColors
}