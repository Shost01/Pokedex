package com.nathan.pokedex.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nathan.pokedex.R
import com.nathan.pokedex.network.RetrofitClient
import com.nathan.pokedex.network.PokemonApiService
import com.nathan.pokedex.repository.PokemonRepository
import com.nathan.pokedex.viewmodel.PokemonListViewModel

class PokemonListFragment : Fragment() {

    private lateinit var viewModel: PokemonListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val retrofit = RetrofitClient.getRetrofit(requireContext())
        val apiService = retrofit.create(PokemonApiService::class.java)
        val repository = PokemonRepository(apiService)

        viewModel = ViewModelProvider(this, PokemonListViewModelFactory(repository))
            .get(PokemonListViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewPokemons)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.pokemonList.observe(viewLifecycleOwner) { list ->
            // Configure o Adapter e trate o clique em cada item
            val adapter = PokemonAdapter(list) { selectedPokemon ->
                // Aqui você pode navegar para a tela de detalhes.
                // Exemplo: abrir um Fragment ou Activity, passando o nome ou id.
                // Para esse exemplo, vamos mostrar um Toast com o nome do Pokémon.
                Toast.makeText(requireContext(), "Selecionado: ${selectedPokemon.name}", Toast.LENGTH_SHORT).show()
            }
            recyclerView.adapter = adapter
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        // Carrega a lista de Pokémons
        viewModel.fetchPokemonList()

    }
}