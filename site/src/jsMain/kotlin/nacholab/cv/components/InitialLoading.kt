package nacholab.cv.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.icons.fa.FaSpinner
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import nacholab.cv.theme.CVTheme
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.s

val spinnerAnimationKeyframes = Keyframes{
    from { Modifier.rotateZ(0f.deg) }
    to { Modifier.rotateZ(360.deg) }
}

@Composable
fun InitialLoading(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(CVTheme.colors.background)
    ) {
        FaSpinner(
            size = IconSize.X10,
            modifier = Modifier
                .color(CVTheme.colors.primary)
                .animation(
                    spinnerAnimationKeyframes.toAnimation(
                        duration = 1.s,
                        iterationCount = AnimationIterationCount.Infinite,
                        timingFunction = AnimationTimingFunction.steps(8)
                    )
                )
        )
    }
}