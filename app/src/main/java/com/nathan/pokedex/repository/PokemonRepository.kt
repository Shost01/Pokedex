package com.nathan.pokedex.repository


import com.nathan.pokedex.model.PokemonDetails
import com.nathan.pokedex.model.PokemonListResponse
import com.nathan.pokedex.network.PokemonApiService


class PokemonRepository(private val pokemonApiService: PokemonApiService) {

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse {
        return pokemonApiService.getPokemonList(limit, offset)
    }

    suspend fun getPokemonDetails(identifier: String): PokemonDetails {
        return pokemonApiService.getPokemonDetails(identifier)
    }
}