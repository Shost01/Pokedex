package com.nathan.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonDetails (
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String
 )