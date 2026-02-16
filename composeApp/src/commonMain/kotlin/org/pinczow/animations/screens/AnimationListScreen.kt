package org.pinczow.animations.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.pinczow.animations.model.Animation
import org.pinczow.animations.repository.AnimationRepositoryImpl
import org.pinczow.animations.viewmodels.AnimationListViewModel

@Composable
fun AnimationListScreen(
    modifier: Modifier = Modifier,
    viewModel: AnimationListViewModel = viewModel {
        AnimationListViewModel(
            AnimationRepositoryImpl()
        )
    },
    onAnimationSelected: (Long) -> Unit
) {
    val animations by viewModel.animations.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier,
        contentPadding = LocalPadding.current
    ) {
        items(animations) { animation ->
            Text(
                text = animation.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onAnimationSelected(animation.id)
                    }
                    .padding(16.dp)
            )
        }
    }
}