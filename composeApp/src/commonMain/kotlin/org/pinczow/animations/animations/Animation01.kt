package org.pinczow.animations.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import org.pinczow.animations.screens.LocalPadding

@Composable
fun Animation01() {
    val padding = LocalPadding.current
    var expanded by remember { mutableStateOf(false) }

    val easing = FastOutLinearInEasing

    val scaleX_ by animateFloatAsState(
        targetValue = if(expanded) 1.0f else 0.0f,
        animationSpec = tween(
            durationMillis = duration,
            easing = easing,
        )
    )
    val scaleY_ by animateFloatAsState(
        targetValue = if(expanded) 1.0f else 0.0f,
        animationSpec = tween(
            durationMillis = duration,
            easing = easing,
        )
    )

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            while(true) {
                delay(2 * duration.toLong())
                expanded = !expanded
            }
        }
    }

    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 8.dp)
        ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    8.dp
                )
            // .padding(all = 0.dp)
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
                            easing = easing,
                        )
                    ),
                    exit = shrinkVertically(
                        shrinkTowards = Alignment.Top,
                        animationSpec = tween(
                            durationMillis = duration,
                            easing = easing,
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
            Row( // Row 1
                modifier = Modifier
                    .height(80.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        //.padding(end = 8.dp)
                        .weight(1f, fill = true),
                    progress = { 0.66f }
                )
                AnimatedVisibility(
                    visible = expanded,
                    enter = expandHorizontally(
                        expandFrom = Alignment.End,
                        animationSpec = tween(
                            durationMillis = duration,
                            easing = easing,
                        )
                    ) /*+ expandVertically(
                        expandFrom = Alignment.CenterVertically,
                        animationSpec = tween(
                            durationMillis = duration,
                            easing = LinearEasing,
                        )
                    )*/,
                    exit = shrinkHorizontally(
                        shrinkTowards = Alignment.End,
                        animationSpec = tween(
                            durationMillis = duration,
                            easing = easing,
                        )
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                        ,
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Button(
                            modifier = Modifier
                                .graphicsLayer {
                                    scaleX = scaleX_
                                    scaleY = scaleY_
                                }
                            ,
                            onClick = { }
                        ) {
                            Text("Click")
                        }

                    }
                }

            }
        }

    }

}