package hu.bme.aut.hf_ftc_teke

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.hf_ftc_teke.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.style.Theme_HF_FTC_Teke)

        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnEdzestervek.setOnClickListener(){
            val profileIntent = Intent(this, TrainingScheduleActivity::class.java)
            startActivity(profileIntent)
        }

        binding.btnOsszes.setOnClickListener(){
            val profileIntent = Intent(this, AllMatchesActivity::class.java)
            startActivity(profileIntent)
        }

        binding.btnMeccsek.setOnClickListener(){
            val profileIntent = Intent(this, MatchesActivity::class.java)
            startActivity(profileIntent)
        }

        binding.btnAtlagok.setOnClickListener(){
            val profileIntent = Intent(this, AverageActivity::class.java)
            startActivity(profileIntent)
        }

        binding.btnEdzesEredmenyek.setOnClickListener(){
            val profileIntent = Intent(this, TrainingResultActivity::class.java)
            startActivity(profileIntent)
        }
    }
}