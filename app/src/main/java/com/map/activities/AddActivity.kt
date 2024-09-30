package com.map.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.map.Data
import com.map.MyApplication
import com.map.R
import com.map.db.UserDao
import com.map.models.User
import java.time.LocalDate
import java.util.Calendar

class AddActivity : AppCompatActivity() {
    lateinit var edtUsername : EditText
    lateinit var edtPassword : EditText
    lateinit var edtFullname : EditText
    lateinit var edtEmail : EditText
    lateinit var btnAdd: Button
    lateinit var spnGender : Spinner
    lateinit var imgDate : ImageView
    lateinit var edtDob : EditText
    lateinit var date : LocalDate
    lateinit var gender : String
    lateinit var userDao: UserDao
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        initView()
        userDao = (application as MyApplication).userDao
        btnAdd.setOnClickListener {
            val username= edtUsername.text.toString().trim()
            val password= edtPassword.text.toString().trim()
            val fullname= edtFullname.text.toString().trim()
            val email= edtEmail.text.toString().trim()
            if(username.equals("") || password.equals("")|| fullname.equals("") || email.equals("")|| date == null || gender==null){
                Toast.makeText(this@AddActivity,"Dien day du o trong",Toast.LENGTH_SHORT).show()
            }else{
                userDao.addUser(User(0,username,password,fullname,email,date,gender))
                Toast.makeText(this@AddActivity,"Them user thanh cong",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@AddActivity,HomeActivity::class.java))
                Log.d("checkAdd",Data.list.toString())
            }
        }
        imgDate.setOnClickListener {
            showDatePickerDialog()
        }
        spnGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                gender = selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                val localDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDayOfMonth)
                date = localDate
                edtDob.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun initView() {
        edtUsername = findViewById(R.id.edtUsername1)
        edtPassword = findViewById(R.id.edtPassword1)
        edtFullname = findViewById(R.id.edtFullname1)
        edtEmail = findViewById(R.id.edtEmail1)
        btnAdd = findViewById(R.id.btnAdd1)
        edtDob = findViewById(R.id.edtDob)
        spnGender = findViewById(R.id.spnGender)
        imgDate = findViewById(R.id.imgDate)
        val items = arrayOf("Male", "Female", "Other")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        spnGender.adapter = adapter
    }
}