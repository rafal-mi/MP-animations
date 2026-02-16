package org.pinczow.animations.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
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
import org.pinczow.animations.screens.LocalPadding

@Composable
fun Animation02() {
    val padding = LocalPadding.current
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

    val buttonHeight by animateDpAsState(
        targetValue = if(visible) 40.dp else 0.dp,
        animationSpec = tween(
            durationMillis = duration,
            easing = FastOutLinearInEasing,
        )
    )

    Row( // Row 2
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
                    .height(buttonHeight)
                ,
                onClick = {  }
            ) {
                Text("Click")
            }
        }

    }

}

