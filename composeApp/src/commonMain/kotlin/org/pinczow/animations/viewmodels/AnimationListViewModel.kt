package org.pinczow.animations.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.pinczow.animations.animations.Animation01
import org.pinczow.animations.animations.Animation02
import org.pinczow.animations.model.Animation
import org.pinczow.animations.repository.AnimationRepository

class AnimationListViewModel(
    private val animationRepository: AnimationRepository
): ViewModel() {
    private val _animations = MutableStateFlow(
        animationRepository.animations
    )

    val animations = _animations.asStateFlow()

}