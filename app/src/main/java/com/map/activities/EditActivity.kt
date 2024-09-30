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
import java.time.format.DateTimeFormatter
import java.util.Calendar

class EditActivity : AppCompatActivity() {
    lateinit var username : String

    lateinit var tvUsername : TextView
    lateinit var edtPassword : EditText
    lateinit var edtFullname : EditText
    lateinit var edtEmail : EditText
    lateinit var btnReset: Button
    lateinit var btnSave: Button
    lateinit var spnGender : Spinner
    lateinit var imgDate : ImageView
    lateinit var edtDob : EditText
    lateinit var date : LocalDate
    lateinit var gender : String
    lateinit var userDao : UserDao
    lateinit var user : User
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        initView()
        userDao = (application as MyApplication).userDao
        username = intent.getStringExtra("username").toString()
        user = userDao.getUserByUsername(username)!!
        date = user.dob
        if(user != null){
            resetEditText(user)
        }
        btnReset.setOnClickListener {
            if (user != null) {
                resetEditText(user)
            }
        }
        btnSave.setOnClickListener {
            val username= tvUsername.text.toString().trim()
            val password= edtPassword.text.toString().trim()
            val fullname= edtFullname.text.toString().trim()
            val email= edtEmail.text.toString().trim()
            if(username.equals("") || password.equals("")|| fullname.equals("") || email.equals("")){
                Toast.makeText(this@EditActivity,"Dien day du o trong", Toast.LENGTH_SHORT).show()
            }else{
                gender = spnGender.selectedItem.toString()
                userDao.updateUserByUserId(user.id!!,User(user.id,username,password,fullname,email,date,gender))
                Toast.makeText(this@EditActivity,"Edit user thanh cong", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@EditActivity,HomeActivity::class.java))
                Log.d("checkUpdate",Data.list.toString())
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun resetEditText(user: User) {
        tvUsername.setText(user.username)
        edtPassword.setText(user.password)
        edtFullname.setText(user.fullname)
        edtEmail.setText(user.email)
        val position = when(user.gender){
            "Male"->0
            "Male"->1
            "Male"->2
            else->-1
        }
        spnGender.setSelection(position)
        edtDob.setText(Data.formatDate(user.dob))
    }

    private fun initView() {
        tvUsername = findViewById(R.id.tvUsername2)
        edtPassword = findViewById(R.id.edtPassword2)
        edtFullname = findViewById(R.id.edtFullname2)
        edtEmail = findViewById(R.id.edtEmail2)
        btnReset = findViewById(R.id.btnReset)
        btnSave = findViewById(R.id.btnSave)
        edtDob = findViewById(R.id.edtDob2)
        spnGender = findViewById(R.id.spnGender2)
        imgDate = findViewById(R.id.imgDate2)
        val items = arrayOf("Male", "Female", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spnGender.adapter = adapter
    }
}