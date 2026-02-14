package org.pinczow.animations.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.pinczow.animations.animations.Animation01
import org.pinczow.animations.model.Animation

class AnimationListViewModel: ViewModel() {
    private val _animations = MutableStateFlow(
        listOf(
            Animation(
                id = 1L,
                name = "Animation 1",
                description = "Description 1",
                implementation = { Animation01() }
            ),
            Animation(
                id = 2L,
                name = "Animation 1",
                description = "Description 2",
                implementation = { Animation01() }
            )
        )
    )

    val animations = _animations.asStateFlow()

}