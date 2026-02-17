package org.pinczow.animations.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.pinczow.animations.Constants.Companion.duration
import kotlin.text.toLong

@Composable
fun Animation08() {
    var expanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            while(true) {
                delay(2 * duration.toLong())
                expanded = !expanded
            }
        }
    }

    Row( // Row 8
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
    ) {
        ElevatedCard(
            modifier = Modifier
                .padding(horizontal = 12.dp)
            ,
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
            ) {
                Row() {
                    Text("#2")
                }
                Row() {
                    AnimatedVisibility(
                        visible = expanded,
                        enter = expandVertically(
                            expandFrom = Alignment.Top,
                            animationSpec = tween(
                                durationMillis = duration,
                                easing = FastOutLinearInEasing,
                            )
                        ),
                        exit = shrinkVertically(
                            shrinkTowards = Alignment.Top,
                            animationSpec = tween(
                                durationMillis = duration,
                                easing = FastOutLinearInEasing,
                            )
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        // .height(80.dp)
                        .background(Color.Gray)
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProgressAnimation()
                }
            }
        }
    }
}

@Composable
fun ProgressAnimation(
    modifier: Modifier = Modifier
) {
    var show by remember { mutableStateOf(false) }

    val progress by animateFloatAsState(
        if (show) 1f else 0f,
        animationSpec = tween(duration, easing = LinearEasing)
    )

    var scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            while(true) {
                delay(2 * duration.toLong())
                show = !show
            }
        }
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier  = Modifier
            // .height(80.dp)
        ) {
            ProgressBarLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
                    .background(Color.DarkGray),
                progress = progress
            ) {
                LinearProgressIndicator(
                    progress = { 0.75f }
                )
                Button(
                    onClick = {}
                ) {
                    Text("Click")
                }

            }
        }
    }

}

@Composable
fun ProgressBarLayout(
    modifier: Modifier,
    progress: Float,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        require(measurables.size == 2)

        val w = measurables[1].minIntrinsicWidth(height = constraints.minHeight)
        val h = measurables[1].minIntrinsicHeight(width = constraints.minWidth)

        val buttonPlaceable =
            measurables.last().measure(
//                 Constraints.fixedWidth((constraints.maxWidth * progress).toInt())

//                constraints.copy(
//                    minWidth = 0, maxHeight = (constraints.maxHeight * progress).toInt()
//                )

                Constraints.fixed(
                    width = (w * progress).toInt(),
                    height = (h * progress).toInt()
                )
            )

        val progressPlaceable = measurables.first().measure(
            Constraints.fixedWidth((constraints.maxWidth - buttonPlaceable.width).toInt())
        )

        val height = buttonPlaceable.height

        layout(
            constraints.maxWidth, height
        ) {

            val width = progressPlaceable.width

            progressPlaceable.placeRelative(
                x = 0,
                y = height / 2
            )
            buttonPlaceable.placeRelative(
                x = width,
                y = height / 2 - buttonPlaceable.height / 2
            )

        }

    }
}

