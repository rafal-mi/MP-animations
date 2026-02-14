package org.pinczow.animations.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    @Serializable
    data object AnimationList: Route, NavKey {
    }

    @Serializable
    data class Animation(val name: String): Route, NavKey
}


