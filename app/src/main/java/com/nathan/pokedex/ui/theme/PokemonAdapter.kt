import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nathan.pokedex.R
import com.nathan.pokedex.model.PokemonResult

class PokemonAdapter(
    private val pokemonList: List<PokemonResult>,
    private val onItemClick: (String) -> Unit
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
        holder.textName.text = pokemon.name.replaceFirstChar { it.uppercase() }
        holder.textId.text = "ID: ${pokemon.url.trimEnd('/').substringAfterLast('/')}"
        holder.itemView.setOnClickListener {
            onItemClick(pokemon.name)
        }
    }

    override fun getItemCount() = pokemonList.size
}