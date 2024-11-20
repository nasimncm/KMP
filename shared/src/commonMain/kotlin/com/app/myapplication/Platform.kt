package com.app.myapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform