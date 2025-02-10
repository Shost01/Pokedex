package com.nathan.pokedex.ui.fragment;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.nathan.pokedex.R
import com.nathan.pokedex.viewmodel.PokemonDetailsViewModel

class PokemonDetailsFragment : Fragment() {

    private lateinit var viewModel: PokemonDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonIdentifier = arguments?.getString("pokemonIdentifier") ?: run {
            Toast.makeText(requireContext(), "Identificador do Pokémon não encontrado", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel = ViewModelProvider(this).get(PokemonDetailsViewModel::class.java)

        val imageView = view.findViewById<ImageView>(R.id.imagePokemon)
        val textName = view.findViewById<TextView>(R.id.textPokemonName)
        val textDetails = view.findViewById<TextView>(R.id.textPokemonDetails)

        viewModel.pokemonDetails.observe(viewLifecycleOwner) { details ->
            textName.text = details.name.replaceFirstChar { it.uppercase() }
            textDetails.text = "ID: ${details.id}\nAltura: ${details.height}\nPeso: ${details.weight}"
            imageView.load(details.sprites.frontDefault) {
                placeholder(R.drawable.ic_pokemon_placeholder)
                error(R.drawable.ic_error)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        viewModel.fetchPokemonDetails(pokemonIdentifier)
    }
}