package org.pinczow.animations.repository

import org.pinczow.animations.animations.Animation01
import org.pinczow.animations.animations.Animation02
import org.pinczow.animations.animations.Animation03
import org.pinczow.animations.animations.Animation04
import org.pinczow.animations.animations.Animation05
import org.pinczow.animations.animations.Animation06
import org.pinczow.animations.animations.Animation07
import org.pinczow.animations.animations.Animation08
import org.pinczow.animations.animations.Animation09
import org.pinczow.animations.animations.Animation10
import org.pinczow.animations.animations.Animation11
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
            ),
            Animation(
                id = 3L,
                name = "Animation 3",
                description = "Description 3",
                implementation = { Animation03() }
            ),
            Animation(
                id = 4L,
                name = "Animation 4",
                description = "Description 4",
                implementation = { Animation04() }
            ),
            Animation(
                id = 5L,
                name = "Animation 5",
                description = "Description 5",
                implementation = { Animation05() }
            ),
            Animation(
                id = 6L,
                name = "Animation 6",
                description = "Description 6",
                implementation = { Animation06() }
            ),
            Animation(
                id = 7L,
                name = "Animation 7",
                description = "Description 7",
                implementation = { Animation07() }
            ),
            Animation(
                id = 8L,
                name = "Animation 8",
                description = "Description 8",
                implementation = { Animation08() }
            ),
            Animation(
                id = 9L,
                name = "Animation 9",
                description = "Description 9",
                implementation = { Animation09() }
            ),
            Animation(
                id = 10L,
                name = "Animation 10",
                description = "Description 10",
                implementation = { Animation10() }
            ),
            Animation(
                id = 11L,
                name = "Animation 11",
                description = "Description 11",
                implementation = { Animation11() }
            ),
        )
}