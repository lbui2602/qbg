package com.map.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.map.Data
import com.map.R

class MainActivity : AppCompatActivity() {
    lateinit var edtUsername : EditText
    lateinit var edtPassword : EditText
    lateinit var btnLogin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        Data.fakeData()
        btnLogin.setOnClickListener {
            if(edtUsername.text.toString().trim().equals("B21DCAT155") && edtPassword.text.toString().trim().equals("17012003")){
                val intent = Intent(this, HomeActivity::class.java)
                Data.username = edtUsername.text.toString().trim()
                startActivity(intent)
            }
            else{
                Toast.makeText(this@MainActivity,"Login khong thanh cong",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initView() {
        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
    }
}