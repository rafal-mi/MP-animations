package org.pinczow.animations

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform