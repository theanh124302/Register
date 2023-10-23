package com.ntv1402.validationregister

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.DialogInterface
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ntv1402.validationregister.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
    }

    private fun initEvent() {
        val myCalendar: Calendar = Calendar.getInstance()
        val date = OnDateSetListener { view: DatePicker?, year: Int, month: Int, day: Int ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            val myFormat = "dd/MM/yyyy"
            val dateFormat = SimpleDateFormat(myFormat, Locale.ROOT)
            binding.etBirthday.setText(dateFormat.format(myCalendar.time))
        }
        binding.btnSelectBirthday.setOnClickListener{
            DatePickerDialog(
                this@MainActivity,
                date, myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        binding.btnSubmit.setOnClickListener{
            checkFormIsValid()
        }
    }

    private fun checkFormIsValid(): Boolean {
        if( binding.etBirthday.text.isBlank()   ||
            binding.etAddress.text.isBlank()    ||
            binding.etEmail.text.isBlank()      ||
            binding.etFirstName.text.isBlank()  ||
            binding.etLastName.text.isBlank()   ||
            !binding.cbAgreeTerms.isChecked     ||
            binding.rdoSex.checkedRadioButtonId == 0
        ){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setMessage("Please input all field")
                .setTitle("Error")
                .setPositiveButton("Accept") { dialog, which ->
                    dialog.cancel()
                }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        } else {
            Toast.makeText(applicationContext, "Form is valid", Toast.LENGTH_LONG).show()
        }
        return true
    }
}
