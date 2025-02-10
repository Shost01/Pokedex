package com.nathan.pokedex.ui.fragment;

import PokemonAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nathan.pokedex.R
import com.nathan.pokedex.network.PokemonApiService
import com.nathan.pokedex.network.RetrofitClient
import com.nathan.pokedex.repository.PokemonRepository
import com.nathan.pokedex.viewmodel.PokemonListViewModel
import com.nathan.pokedex.viewmodel.PokemonListViewModelFactory

class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list) {

    private lateinit var viewModel: PokemonListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicie o RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewPokemons)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicialize o repositório (ou pegue de uma instância pré-existente)
        val pokemonApiService = RetrofitClient.createService(PokemonApiService::class.java)
        val repository = PokemonRepository(pokemonApiService)

        // Crie a fábrica para passar o repositório
        val factory = PokemonListViewModelFactory(repository, pokemonApiService)

        // Use a fábrica para criar o ViewModel
        viewModel = ViewModelProvider(this, factory).get(PokemonListViewModel::class.java)

        // Observe o estado da lista de Pokémon
        viewModel.pokemonList.observe(viewLifecycleOwner) { list ->
            val adapter = PokemonAdapter(list) { pokemonName ->
                // Navegar para a tela de detalhes
                findNavController().navigate(
                    R.id.action_pokemonListFragment_to_pokemonDetailsFragment,
                    bundleOf("pokemonIdentifier" to pokemonName)
                )
            }
            recyclerView.adapter = adapter
        }

        // Observe mensagens de erro
        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        // Chame o método para buscar a lista de Pokémon
        viewModel.fetchPokemonList()
    }
}
