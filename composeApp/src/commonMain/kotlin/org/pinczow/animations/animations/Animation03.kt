package org.pinczow.animations.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.pinczow.animations.Constants.Companion.duration

@Composable
fun Animation03() {
    var visible by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            while(true) {
                delay(2 * duration.toLong())
                visible = !visible
            }
        }
    }

    Row( // Row 3
        modifier = Modifier
            .height(80.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LinearProgressIndicator(
            modifier = Modifier.weight(1f, fill = true),
            progress = { 0.66f }
        )
        AnimatedVisibility(
            visible = visible,
            enter = expandHorizontally(
                expandFrom = Alignment.End,
                animationSpec = tween(
                    durationMillis = duration,
                    easing = FastOutLinearInEasing,
                )
            ) /*+ expandVertically(
                expandFrom = Alignment.Top,
                animationSpec = tween(
                    durationMillis = duration,
                    easing = FastOutLinearInEasing,
                )
            )*/,
            exit = shrinkHorizontally(
                shrinkTowards = Alignment.End,
                animationSpec = tween(
                    durationMillis = duration,
                    easing = FastOutLinearInEasing,
                )
            )
        ) {
            Button(
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = duration,
                            easing = FastOutLinearInEasing,
                        )
                    )
                    .height(if (visible) 40.dp else 0.dp)
                ,
                onClick = {  }
            ) {
                Text("Click")
            }
        }

    }

}