package org.pinczow.animations.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.pinczow.animations.viewmodels.AnimationListViewModel

@Composable
fun AnimationListScreen(
    modifier: Modifier = Modifier,
    viewModel: AnimationListViewModel = viewModel {
        AnimationListViewModel()
    },
    onAnimationSelected: (String) -> Unit
) {

}