package org.pinczow.animations.animations

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import org.pinczow.animations.Constants.Companion.duration

@Composable
fun Animation11() {
    ButtonAnimation()
}

/**
 * https://stackoverflow.com/questions/79389168/how-to-animate-button-width-changes-smoothly-when-showing-hiding-another-button
 */
@Composable
fun ButtonAnimation() {
    var show by remember { mutableStateOf(false) }

    val progress by animateFloatAsState(
        if (show) 1f else 0f,
        animationSpec = tween(duration)
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = { show = !show }) {
            Text(text = "Click")
        }

        Spacer(modifier = Modifier.padding(32.dp))

        ButtonAnimationLayout(
            modifier = Modifier.fillMaxWidth().border(2.dp, Color.Red),
            progress = progress
        ) {
            OutlinedButton(
                modifier = Modifier.padding(end = 8.dp),
                onClick = {}
            ) {
                Text("Button 2")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text("Button 1", modifier = Modifier.animateContentSize())
            }
        }
    }
}

@Composable
fun ButtonAnimationLayout(
    modifier: Modifier,
    progress: Float,
    content: @Composable () -> Unit
) {

    Layout(
        modifier = modifier,

        content = content
    ) { measurables, constraints ->
        require(measurables.size == 2)

        val mobileButtonPlaceable =
            measurables.first().measure(constraints.copy(minWidth = 0))

        val stationaryButtonPlaceable = measurables.last().measure(
            Constraints.fixedWidth((constraints.maxWidth - mobileButtonPlaceable.width * progress).toInt())
        )

        layout(
            constraints.maxWidth, stationaryButtonPlaceable.height
        ) {

            val width = mobileButtonPlaceable.width
            val leftPadding = 16.dp.roundToPx()

            mobileButtonPlaceable.placeRelative(
                x = (-(width + leftPadding) * (1 - progress)).toInt(),
                y = 0
            )

            stationaryButtonPlaceable.placeRelative(
                x = ((width) * progress).toInt(),
                y = 0
            )
        }

    }
}
