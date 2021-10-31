package com.example.chuckjokes.jokes

import androidx.recyclerview.widget.RecyclerView
import com.example.chuckjokes.jokes.RecyclerAdapter.VH
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.chuckjokes.R
import com.example.chuckjokes.databinding.JokeItemBinding
import java.util.ArrayList

class RecyclerAdapter(private val wireframe: JokesFragment) : RecyclerView.Adapter<VH>() {
    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: JokeItemBinding = JokeItemBinding.bind(itemView)
    }

    private var items: List<JokesModel.JokeItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.joke_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.binding.jokeID.text = wireframe.resources.getString(R.string.joke_id_symbol, item.id)
        holder.binding.categories.text = item.categories
        holder.binding.jokeText.text = item.text
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(items: List<JokesModel.JokeItem>) {
        this.items = items
        notifyDataSetChanged()
        // TODO: проверить этот варнинг. Не переделать ли это в ArrayList чтобы, указать, что не все эелементы обновились, а только некоторые. Ну и обновлять не весь лист, а часть.
    }
}