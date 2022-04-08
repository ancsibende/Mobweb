package hu.bme.aut.hf_ftc_teke

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.hf_ftc_teke.adapter.MatchesAdapter
import hu.bme.aut.hf_ftc_teke.data.MatchesItemDatabase
import hu.bme.aut.hf_ftc_teke.data.MatchesItems
import hu.bme.aut.hf_ftc_teke.databinding.ActivityAllMatchesBinding
import kotlin.concurrent.thread

class AllMatchesActivity : AppCompatActivity(),
MatchesAdapter.MatchesItemClickListener{
    private lateinit var binding: ActivityAllMatchesBinding

    private lateinit var database: MatchesItemDatabase
    private lateinit var adapter: MatchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllMatchesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        database = MatchesItemDatabase.getDatabase(applicationContext)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = MatchesAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.matchesItemDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: MatchesItems) {
        thread {
            database.matchesItemDao().update(item)
            Log.d("AllMatchesActivity", "MatchesItems update was successful")
        }
    }
}
