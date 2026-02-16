package org.pinczow.animations.repository

import org.pinczow.animations.model.Animation

interface AnimationRepository {
    val animations: List<Animation>
}