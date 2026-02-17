package org.pinczow.animations.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
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
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.pinczow.animations.Constants.Companion.duration
import kotlin.text.toInt
import kotlin.text.toLong

@Composable
fun Animation06() {
    var expanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // for(let i = 1; i < 10; i++) { let x = i * 0.1; let y = 1 - Math.pow(x, 1/3); console.log(`${y.toFixed(3)}f at (${x.toFixed(3)} * duration).toInt()  using LinearEasing`); }

    val animationSpec = if(expanded) keyframes {
        durationMillis = duration
        0.0f at 0 using LinearEasing // for 0-15 ms
        0.001f at (0.100 * duration).toInt()  using LinearEasing
        0.008f at (0.200 * duration).toInt()  using LinearEasing
        0.027f at (0.300 * duration).toInt()  using LinearEasing
        0.064f at (0.400 * duration).toInt()  using LinearEasing
        0.125f at (0.500 * duration).toInt()  using LinearEasing
        0.216f at (0.600 * duration).toInt()  using LinearEasing
        0.343f at (0.700 * duration).toInt()  using LinearEasing
        0.512f at (0.800 * duration).toInt()  using LinearEasing
        0.729f at (0.900 * duration).toInt()  using LinearEasing
    } else keyframes {
        durationMillis = duration
        1.0f at 0 using LinearEasing
        0.536f at (0.100 * duration).toInt()  using LinearEasing
        0.415f at (0.200 * duration).toInt()  using LinearEasing
        0.331f at (0.300 * duration).toInt()  using LinearEasing
        0.263f at (0.400 * duration).toInt()  using LinearEasing
        0.206f at (0.500 * duration).toInt()  using LinearEasing
        0.157f at (0.600 * duration).toInt()  using LinearEasing
        0.112f at (0.700 * duration).toInt()  using LinearEasing
        0.072f at (0.800 * duration).toInt()  using LinearEasing
        0.035f at (0.900 * duration).toInt()  using LinearEasing
    }

    val buttonScaleX by animateFloatAsState(
        targetValue = if(expanded) 1.0f else 0.0f,
        animationSpec = tween(
            durationMillis = duration
        )
    )
    val buttonScaleY by animateFloatAsState(
        targetValue = if(expanded) 1.0f else 0.0f,
        animationSpec = animationSpec
    )

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            while(true) {
                delay(2 * duration.toLong())
                expanded = !expanded
            }
        }
    }

    Row( // Row 6
        modifier = Modifier
            .padding(all = 8.dp)
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
                    Text("#1")
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
                        .height(80.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .weight(1f, fill = true)
                            .padding(end = 8.dp)
                        ,
                        progress = { 0.66f }
                    )
                    Button(
                        modifier = Modifier
//                                .width((120 * buttonScaleX).dp)
//                                .height((40 * buttonScaleX).dp)
                            .graphicsLayer {
                                scaleX = buttonScaleX
                                scaleY = buttonScaleX
                            }
                        ,
                        onClick = {  }
                    ) {
                        Text("Click")
                    }
                }
            }
        }
    }

}