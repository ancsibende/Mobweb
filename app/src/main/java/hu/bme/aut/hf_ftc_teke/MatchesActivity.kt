package hu.bme.aut.hf_ftc_teke

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.hf_ftc_teke.adapter.MatchesAdapter
import hu.bme.aut.hf_ftc_teke.data.MatchesItemDatabase
import hu.bme.aut.hf_ftc_teke.data.MatchesItems
import hu.bme.aut.hf_ftc_teke.databinding.ActivityMatchesBinding
import kotlin.concurrent.thread

class MatchesActivity : AppCompatActivity(), MatchesAdapter.MatchesItemClickListener,
    AdapterView.OnItemSelectedListener{

    private lateinit var binding: ActivityMatchesBinding
    private lateinit var lista:Array<String>

    private  var fo:Int=1

    private lateinit var database: MatchesItemDatabase
    private lateinit var adapter1: MatchesAdapter
    private lateinit var adapter2: MatchesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        lista=resources.getStringArray(R.array.Fordulok)

        database = MatchesItemDatabase.getDatabase(applicationContext)
        initRecyclerView()

        binding.spinner.onItemSelectedListener = this
        val aa = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, lista)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = aa
    }

    private fun initRecyclerView() {
        adapter1 = MatchesAdapter(this)
        binding.rvHazai.layoutManager = LinearLayoutManager(this)
        binding.rvHazai.adapter = adapter1

        adapter2 = MatchesAdapter(this)
        binding.rvVendeg.layoutManager = LinearLayoutManager(this)
        binding.rvVendeg.adapter = adapter2
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items1 = database.matchesItemDao().getFordulo(fo,0)
            val items2=database.matchesItemDao().getFordulo(fo,1)
            runOnUiThread {
                adapter1.update(items1)
                adapter2.update(items2)
            }
        }
    }

    override fun onItemChanged(item: MatchesItems) {
        thread {
            database.matchesItemDao().update(item)
            Log.d("MatchesActivity", "MatchesItem update was successful")
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        //fo=list_of_items[position].toInt()
        fo=lista[position].toInt()
        loadItemsInBackground()
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

}