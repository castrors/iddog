package io.github.castrors.iddog.data.model

import io.github.castrors.iddog.presentation.model.Dog

data class DogEntity(private val url: String) {
    fun toDog() = Dog(url)
}