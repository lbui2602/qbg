package com.map.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.map.Data
import com.map.MyApplication
import com.map.R
import com.map.db.DatabaseHelper
import com.map.db.UserDao

class MainActivity : AppCompatActivity() {
    lateinit var edtUsername : EditText
    lateinit var edtPassword : EditText
    lateinit var btnLogin : Button
    lateinit var userDao :UserDao
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userDao = (application as MyApplication).userDao
        userDao.fakeData()
        initView()
        btnLogin.setOnClickListener {
//            if(edtUsername.text.toString().trim().equals("B21DCAT155") && edtPassword.text.toString().trim().equals("17012003")){
//                val intent = Intent(this, HomeActivity::class.java)
//                Data.username = edtUsername.text.toString().trim()
//                startActivity(intent)
//            }
//            else{
//                Toast.makeText(this@MainActivity,"Login khong thanh cong",Toast.LENGTH_SHORT).show()
//            }
            val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
        }
    }

    private fun initView() {
        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
    }
}