package io.github.castrors.iddog.data.model

data class DogContentEntity(
    val category: String,
    val list: List<DogEntity>
) {

    companion object {
        fun empty() = DogContentEntity("invalid_category", listOf())
    }
}
