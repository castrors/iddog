package io.github.castrors.iddog.data.model

data class DogContentEntity(
    val category: String,
    val list: List<String>
) {

    companion object {
        fun empty() = DogContentEntity("invalid_category", listOf())
    }
}
