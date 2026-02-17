package org.pinczow.animations.animations

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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

enum class ButtonState {
    Collapsed,
    Expanded
}

@Composable
fun Animation05() {
    var buttonState by remember { mutableStateOf(ButtonState.Collapsed) }
    val transition = updateTransition(
        targetState = buttonState,
        label = "button state"
    )
    val buttonWidthTr by transition.animateDp(
        label = "border width",
        transitionSpec = {
            tween(durationMillis = 1000)
        }
    ) { state ->
        when (state) {
            ButtonState.Collapsed -> 0.dp
            ButtonState.Expanded -> 120.dp
        }
    }
    val buttonHeightTr by transition.animateDp(
        label = "button height",
        transitionSpec = {
            tween(durationMillis = 1000)
        }
    ) { state ->
        when (state) {
            ButtonState.Collapsed -> 0.dp
            ButtonState.Expanded -> 40.dp
        }
    }

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            while(true) {
                delay(2 * duration.toLong())
                buttonState = if(buttonState == ButtonState.Collapsed) ButtonState.Expanded else ButtonState.Collapsed
            }
        }
    }

    Row( // Row 5
        modifier = Modifier
            .height(80.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LinearProgressIndicator(
            modifier = Modifier.weight(1f, fill = true),
            progress = { 0.66f }
        )
        Button(
            modifier = Modifier
                .width(buttonWidthTr)
                .height(buttonHeightTr)
            ,
            onClick = {  }
        ) {
            Text("Click")
        }
    }


}