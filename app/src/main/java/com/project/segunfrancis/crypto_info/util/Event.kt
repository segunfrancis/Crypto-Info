package com.project.segunfrancis.crypto_info.util

/**
 * Created by SegunFrancis
 */
class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peek(): T? {
        return content
    }
}