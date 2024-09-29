package com.map.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.map.Data
import com.map.R
import com.map.models.User

class HomeActivity : AppCompatActivity() {
    lateinit var username:String
    lateinit var tvUsername : TextView
    lateinit var btnAdd: Button
    lateinit var btnEdit : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var list : List<User> = Data.list
        Log.d("check",list.toString())
        username = "Welcome"+ Data.username
        initView()
        if(username!=null){
            tvUsername.setText(username)
        }
        btnEdit.setOnClickListener {
            startActivity(Intent(this@HomeActivity,SearchActivity::class.java))
        }
        btnAdd.setOnClickListener {
            startActivity(Intent(this@HomeActivity,AddActivity::class.java))
        }
    }
    private fun initView() {
        tvUsername = findViewById(R.id.tvUsername)
        btnAdd = findViewById(R.id.btnAdd)
        btnEdit = findViewById(R.id.btnEdit)
    }
}