package com.nathan.pokedex.ui.theme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nathan.pokedex.R
import com.nathan.pokedex.model.PokemonResult

class PokemonAdapter (
    private val pokemonList: List<PokemonResult>,
    private val onItemClick: (PokemonResult) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.textPokemonName)
        val textId: TextView = itemView.findViewById(R.id.textPokemonId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        // Exibe o nome com a primeira letra maiúscula
        holder.textName.text = pokemon.name.replaceFirstChar { it.uppercase() }

        // Extrai o id da URL (exemplo: "https://pokeapi.co/api/v2/pokemon/25/" -> "25")
        val id = pokemon.url.trimEnd('/').substringAfterLast('/')
        holder.textId.text = "ID: $id"

        holder.itemView.setOnClickListener {
            onItemClick(pokemon)
        }
    }

    override fun getItemCount() = pokemonList.size
}