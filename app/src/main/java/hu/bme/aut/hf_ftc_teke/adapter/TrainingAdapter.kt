package hu.bme.aut.hf_ftc_teke.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.hf_ftc_teke.R
import hu.bme.aut.hf_ftc_teke.data.TrainingItems
import hu.bme.aut.hf_ftc_teke.databinding.ItemTrainingListBinding

class TrainingAdapter(private val listener: TrainingItemClickListener) :
    RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {

    private val items = mutableListOf<TrainingItems>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrainingViewHolder(
        ItemTrainingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val trainingItem = items[position]

        holder.binding.tvDatum.text=trainingItem.datum
        holder.binding.ivHelyszin.setImageResource(getImageResource(trainingItem.helyszin))
        holder.binding.tvEdzesEredmeny.text = "${trainingItem.eredmeny} Fa"
        holder.binding.tvMegjegyzes.text = trainingItem.megjegyzes
        holder.binding.tvCategory.text = trainingItem.helyszin.name

        holder.binding.ibRemove.setOnClickListener { buttonView ->
            listener.onItemDeleted(trainingItem)
        }
    }

    @DrawableRes()
    private fun getImageResource(category: TrainingItems.Category): Int {
        return when (category) {
            TrainingItems.Category.GYOR -> R.drawable.gyor
            TrainingItems.Category.FRADI -> R.drawable.fradi
            TrainingItems.Category.PAPA -> R.drawable.papa
        }
    }

    override fun getItemCount(): Int = items.size

    interface TrainingItemClickListener {
        fun onItemDeleted(item: TrainingItems)
    }

    inner class TrainingViewHolder(val binding: ItemTrainingListBinding) : RecyclerView.ViewHolder(binding.root)

    fun addItem(item: TrainingItems) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(shoppingItems: List<TrainingItems>) {
        items.clear()
        items.addAll(shoppingItems)
        notifyDataSetChanged()
    }

    fun deleteItem(item: TrainingItems){
        val index = items.indexOf(item)
        items.removeAt(index)
        notifyItemRemoved(index)
    }



}