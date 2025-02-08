package com.nathan.pokedex.network

import com.nathan.pokedex.model.PokemonDetails
import com.nathan.pokedex.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    // Endpoint para listar Pokémons (com parâmetros de paginação)
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") ofsset: Int
    ): PokemonListResponse

    // Endpoint para obter detalhes do Pokémon (por nome ou id)
    @GET("pokemon/{identifier}")
    suspend fun getPokemonDetails(
        @Path("identifier") identifier: String
    ): PokemonDetails

}