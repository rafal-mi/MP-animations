package org.pinczow.animations.repository

import org.pinczow.animations.animations.Animation01
import org.pinczow.animations.animations.Animation02
import org.pinczow.animations.model.Animation

class AnimationRepositoryImpl: AnimationRepository {
    override val animations: List<Animation>
        get() = listOf(
            Animation(
                id = 1L,
                name = "Animation 1",
                description = "Description 1",
                implementation = { Animation01() }
            ),
            Animation(
                id = 2L,
                name = "Animation 2",
                description = "Description 2",
                implementation = { Animation02() }
            )
        )
}