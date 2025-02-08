package com.nathan.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nathan.pokedex.model.PokemonResult
import com.nathan.pokedex.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonListViewModel(private  val repository: PokemonRepository) : ViewModel() {

    private val _pokemonList = MutableLiveData<List<PokemonResult>>()
    val pokemonList: LiveData<List<PokemonResult>> = _pokemonList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchPokemonList(limit: Int = 150, offset: Int = 0) {
        viewModelScope.launch{
            try {
                val response = repository.getPokemonList(limit, offset)
                _pokemonList.value = response.results
            } catch (e: Exception) {
                _errorMessage.value = "Erro ao carregar Pokémon: ${e.message}"
            }
        }
    }
}