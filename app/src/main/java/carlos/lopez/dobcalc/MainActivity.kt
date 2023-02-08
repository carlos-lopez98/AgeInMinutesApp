package carlos.lopez.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    //Input Date set as nullable -- making it private makes it only accessible in the Main Class
    private var inputDate: TextView? = null
    private var dateInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var btnDatePicker: Button = findViewById<Button>(R.id.btnDatePicker)
        inputDate = findViewById(R.id.inputDate)
        dateInMinutes = findViewById(R.id.dateInMinutes)
        //When button is clicked run clickDatePicker Function
        btnDatePicker.setOnClickListener(){
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance() //Gives us access to current calendar
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        //On date set listener is the display that pops up
        //When user presses ok, the year, month, and dayOfMonth are stored
        var dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth,selectedDayOfMonth ->
                Toast.makeText(this, "Yeaar was $selectedYear, Month was ${selectedMonth + 1}, day was $selectedDayOfMonth", Toast.LENGTH_LONG).show()

                //Storing our date with correct format in a val
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                inputDate?.text = selectedDate

                //If we want to use this date, we need to put it into a date object, so we can manipulate it to minutes,
                //Then display the minutes in a string format
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH) //Sets our format for our date

                //This sdf is parsing our string into the pattern we located
                val theDate = sdf.parse(selectedDate)

                theDate?.let(){
                    //Time between selected date and 1970
                    var selectedDateInMinutes = theDate.time / 60000
                    //Time between current date and 1970
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let(){
                        val currentDateInMinutes = currentDate.time / 60000
                        //differenceInMinutes is set to type long
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        //Remember to add questionMark for type safety
                        dateInMinutes?.text = differenceInMinutes.toString()
                    }
                }
            },
            year, month, day
        )
        //Sets a max date to the datePicker
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}