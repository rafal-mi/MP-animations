package org.pinczow.animations.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.pinczow.animations.screens.AnimationListScreen
import org.pinczow.animations.screens.AnimationScreen

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.AnimationList::class, Route.AnimationList.serializer())
                    subclass(Route.Animation::class, Route.Animation.serializer())
                }
            }
        },
        Route.AnimationList
    )
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator {
                true
            }
        ),
        entryProvider = entryProvider {
            entry<Route.AnimationList> {
                AnimationListScreen(
                    onAnimationSelected = {
                        backStack.add(Route.Animation(it))
                    }
                )
            }
            entry<Route.Animation> {
                AnimationScreen(
                    name = it.name
                )
            }
        }
    )
}
