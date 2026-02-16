package org.pinczow.animations.viewmodels

import androidx.lifecycle.ViewModel
import org.pinczow.animations.model.Animation
import org.pinczow.animations.repository.AnimationRepository

class AnimationViewModel(
    private val animationRepository: AnimationRepository,
    private val id: Long
): ViewModel() {
    var animation: Animation? = animationRepository.animations.first { it.id == id }
}

data class AnimationState(
    val animation: Animation
)