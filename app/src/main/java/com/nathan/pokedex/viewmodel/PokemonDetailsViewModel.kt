package com.nathan.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nathan.pokedex.model.PokemonDetails
import com.nathan.pokedex.repository.PokemonRepository
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Identifier

class PokemonDetailsViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemonDetails = MutableLiveData<PokemonDetails>()
    val pokemonDetails: LiveData<PokemonDetails> = _pokemonDetails

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchPokemonDetails(identifier: String) {
        viewModelScope.launch {
            try {
                val details = repository.getPokemonDetails(identifier)
                _pokemonDetails.value = details
            } catch (e: Exception) {
                _errorMessage.value = "Erro ao carregar detalhes: ${e.message}"
            }
        }
    }
}