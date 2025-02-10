package com.nathan.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nathan.pokedex.network.PokemonApiService
import com.nathan.pokedex.repository.PokemonRepository

class PokemonListViewModelFactory(private val repository: PokemonRepository, private val pokemonApiService: PokemonApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonListViewModel(repository,pokemonApiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}