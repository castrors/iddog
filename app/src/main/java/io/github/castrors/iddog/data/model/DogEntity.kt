package io.github.castrors.iddog.data.model

import io.github.castrors.iddog.presentation.Dog

data class DogEntity(private val url: String) {
    fun toDog() = Dog(url)
}