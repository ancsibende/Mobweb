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
import hu.bme.aut.hf_ftc_teke.databinding.ActivityAverageBinding
import kotlin.concurrent.thread

class AverageActivity : AppCompatActivity(), MatchesAdapter.MatchesItemClickListener,
AdapterView.OnItemSelectedListener{
    private lateinit var binding: ActivityAverageBinding
    private lateinit var hazai_v_vendeg:Array<String>
    private lateinit var jatekosok:Array<String>
    private  var fo_hv:Int = 0
    private  var fo_j:String=""
    private lateinit var atlag:List<Int>

    private lateinit var database: MatchesItemDatabase
    private lateinit var adapter1: MatchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAverageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(binding.toolbar)

        hazai_v_vendeg=resources.getStringArray(R.array.HazaiVendeg)
        jatekosok=resources.getStringArray(R.array.Jatekosok)

        database = MatchesItemDatabase.getDatabase(applicationContext)
        initRecyclerView()

        binding.spinnerHV.onItemSelectedListener = this
        val aa1 = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, hazai_v_vendeg)
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerHV.adapter = aa1

        binding.spinnerJatekos.onItemSelectedListener = this
        val aa2 = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, jatekosok)
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerJatekos.adapter = aa2
    }

    private fun initRecyclerView() {
        adapter1 = MatchesAdapter(this)
        binding.rvEredmeny.layoutManager = LinearLayoutManager(this)
        binding.rvEredmeny.adapter = adapter1

        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items1 = database.matchesItemDao().getPicked(fo_hv,fo_j)
            atlag=database.matchesItemDao().getAtlag(fo_hv,fo_j)
            if(atlag.average().toString()=="NaN"){
                binding.txtAtlag.text=""
            }else{
                binding.txtAtlag.text=atlag.average().toString()
            }
            runOnUiThread {
                adapter1.update(items1)
            }
        }
    }

    override fun onItemChanged(item: MatchesItems) {
        thread {
            database.matchesItemDao().update(item)
            Log.d("AverageActivity", "MatchesItem update was successful")
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
           if(arg0.id==binding.spinnerHV.id){
               fo_hv=position
               loadItemsInBackground()
           }
            if(arg0.id==binding.spinnerJatekos.id){
                fo_j=jatekosok[position]
                loadItemsInBackground()
            }
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
}