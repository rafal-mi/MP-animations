package org.pinczow.animations.model

import androidx.compose.runtime.Composable

data class Animation(
    val id: Long,
    val name: String,
    val description: String,
    val implementation: @Composable () -> Unit
)
