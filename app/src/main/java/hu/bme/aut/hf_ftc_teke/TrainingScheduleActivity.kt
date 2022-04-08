package hu.bme.aut.hf_ftc_teke

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.hf_ftc_teke.databinding.ActivityTrainingScheduleBinding
import java.util.*

class TrainingScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrainingScheduleBinding

    private val decemberDates=arrayOf(2,7,9,14,16)
    private val trainingSchedules=arrayOf("120 vegyes eredményre","30 teli, 30 állás, 60 vegyes",
        "60 3-as tarolás, 60 vegyes","30 képjáték, 30 tarolás, 60 vegyes","120 egyéni igények szerint")

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingScheduleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val c = Calendar.getInstance()

        binding.btnEdzesterv.setOnClickListener() {
            var found:Boolean=false
            for(index in decemberDates.indices){
              // binding.textView.text="${december_dates[index]}"
                if(binding.datePicker.dayOfMonth== decemberDates[index] &&binding.datePicker.month+1==12){
                    binding.textView.text="${binding.datePicker.month+1}. ${binding.datePicker.dayOfMonth}. edzésterve:" +
                           "\n" + trainingSchedules[index]
                    found=true
                }else if(!found){
                    binding.textView.text="Nincs edzés ezen a napon"
                }
            }
        }
    }
}

