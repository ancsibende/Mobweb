package hu.bme.aut.hf_ftc_teke.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.hf_ftc_teke.R
import hu.bme.aut.hf_ftc_teke.data.MatchesItems
import hu.bme.aut.hf_ftc_teke.databinding.ItemMatchesListBinding

class MatchesAdapter(private val listener: MatchesItemClickListener) :
    RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder>() {

    private val items = mutableListOf<MatchesItems>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MatchesViewHolder(
        ItemMatchesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val matchesItem = items[position]

        holder.binding.tvFordulo.text = matchesItem.fordulo.toString()
        holder.binding.tvNev.text = matchesItem.nev
        if(matchesItem.category==0){
            holder.binding.ivHazai.setImageResource(R.drawable.hazai)
        }else{
            holder.binding.ivHazai.setImageResource(R.drawable.vendeg)
        }
        holder.binding.tvOsszfa.text = "${matchesItem.eredmeny} fa"
    }

    override fun getItemCount(): Int = items.size

    interface MatchesItemClickListener {
        fun onItemChanged(item: MatchesItems)
    }

    inner class MatchesViewHolder(val binding: ItemMatchesListBinding) : RecyclerView.ViewHolder(binding.root)

    fun update(matchesItems: List<MatchesItems>) {
        items.clear()
        items.addAll(matchesItems)
        notifyDataSetChanged()
    }
}