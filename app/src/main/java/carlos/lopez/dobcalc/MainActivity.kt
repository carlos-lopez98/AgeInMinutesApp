package carlos.lopez.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var btnDatePicker: Button = findViewById<Button>(R.id.btnDatePicker)

        //When button is clicked run clickDatePicker Function
        btnDatePicker.setOnClickListener(){
            clickDatePicker()
        }
    }

    fun clickDatePicker(){

        val myCalendar = Calendar.getInstance() //Gives us access to current calendar
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{ view, year, month,dayOfMonth ->
            Toast.makeText(this, "DatePicker Works", Toast.LENGTH_LONG).show()
            },
            year, month, day
            ).show()
    }
}