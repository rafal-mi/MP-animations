package org.pinczow.animations.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import org.pinczow.animations.model.Animation
import org.pinczow.animations.repository.AnimationRepositoryImpl
import org.pinczow.animations.viewmodels.AnimationViewModel

@Composable
fun AnimationScreen(
    id: Long,
    viewModel: AnimationViewModel = viewModel {
        AnimationViewModel(
            AnimationRepositoryImpl(),
            id
        )
    },

) {
    val animation = viewModel.animation

    animation?.implementation?.invoke()


}