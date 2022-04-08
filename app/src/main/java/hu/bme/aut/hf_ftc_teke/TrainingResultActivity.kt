package hu.bme.aut.hf_ftc_teke

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.hf_ftc_teke.adapter.TrainingAdapter
import hu.bme.aut.hf_ftc_teke.data.TrainingItems
import hu.bme.aut.hf_ftc_teke.data.TrainingItemsDatabase
import hu.bme.aut.hf_ftc_teke.databinding.ActivityTrainingResultBinding
import hu.bme.aut.hf_ftc_teke.fragment.NewTrainingItemDialogFragment
import kotlin.concurrent.thread

class TrainingResultActivity : AppCompatActivity(), TrainingAdapter.TrainingItemClickListener,
    NewTrainingItemDialogFragment.NewTrainingItemDialogListener  {
    private lateinit var binding: ActivityTrainingResultBinding

    private lateinit var database: TrainingItemsDatabase
    private lateinit var adapter: TrainingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        database = TrainingItemsDatabase.getDatabase(applicationContext)

        binding.fab.setOnClickListener {
            NewTrainingItemDialogFragment().show(
                supportFragmentManager,
                NewTrainingItemDialogFragment.TAG
            )
        }
        initRecyclerView()
    }


    private fun initRecyclerView() {
        adapter = TrainingAdapter(this)
        binding.rvTrainingResult.layoutManager = LinearLayoutManager(this)
        binding.rvTrainingResult.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.trainingItemDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }


    /*  override fun onItemChanged(item: ShoppingItem) {
          thread {
              database.shoppingItemDao().update(item)
              Log.d("MainActivity", "ShoppingItem update was successful")
          }
      }*/

    override fun onItemDeleted(item: TrainingItems) {
        thread {
            database.trainingItemDao().deleteItem(item)

            runOnUiThread{
                adapter.deleteItem(item)
            }
        }
    }

    override fun onTrainingItemsCreated(newItem: TrainingItems) {
        thread {
            database.trainingItemDao().insert(newItem)

            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }
}