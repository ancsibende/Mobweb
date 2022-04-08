package hu.bme.aut.hf_ftc_teke.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.bme.aut.hf_ftc_teke.R
import hu.bme.aut.hf_ftc_teke.data.TrainingItems
import hu.bme.aut.hf_ftc_teke.databinding.DialogNewTrainingItemBinding

class NewTrainingItemDialogFragment : DialogFragment() {
    interface NewTrainingItemDialogListener {
        fun onTrainingItemsCreated(newItem: TrainingItems)
    }

    private lateinit var listener: NewTrainingItemDialogListener

    private lateinit var binding: DialogNewTrainingItemBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewTrainingItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewShoppingItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewTrainingItemBinding.inflate(LayoutInflater.from(context))
        binding.spHelyszin.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.category_items)
        )

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_training_item)
            .setView(binding.root)
            .setPositiveButton(R.string.button_ok) { dialogInterface, i ->
                if (isValid()) {
                    listener.onTrainingItemsCreated(getTrainingItem())
                }
            }

            .setNegativeButton(R.string.button_cancel, null)
            .create()
    }

    private fun isValid() = binding.etEredmeny.text.isNotEmpty()

    private fun getTrainingItem() = TrainingItems(
        datum = binding.etDatum.text.toString(),
        eredmeny = binding.etEredmeny.text.toString().toIntOrNull() ?: 0,
        helyszin=TrainingItems.Category.getByOrdinal(binding.spHelyszin.selectedItemPosition)
            ?: TrainingItems.Category.FRADI,
        megjegyzes = binding.etMegjegyes.text.toString()

    )

    companion object {
        const val TAG = "NewMatchesItemDialogFragment"
    }
}
