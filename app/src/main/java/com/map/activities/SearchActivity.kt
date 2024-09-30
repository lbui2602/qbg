package com.map.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.map.Data
import com.map.R
import com.map.UserAdapter
import com.map.db.DatabaseHelper
import com.map.db.UserDao
import com.map.models.User

class SearchActivity : AppCompatActivity() {
    lateinit var edtSearch : EditText
    lateinit var btnSearch : Button
    lateinit var listView: ListView
    lateinit var list : List<User>
    lateinit var dbHelper : DatabaseHelper
    lateinit var userDao :UserDao

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
        dbHelper = DatabaseHelper(this)
        userDao = UserDao(dbHelper)
        btnSearch.setOnClickListener {
            val name = edtSearch.text.toString().trim()
            list = userDao.getListUserByName(name)
            if(list.isEmpty()){
                Toast.makeText(this@SearchActivity,"khong tim thay user", Toast.LENGTH_SHORT).show()
            }
            else{
                val adapter = UserAdapter(this, list)
                listView.adapter = adapter
            }
        }
        listView.setOnItemClickListener { parent, view, position, id ->
            val user = list.get(position)
            val intent = Intent(this@SearchActivity,EditActivity::class.java)
            intent.putExtra("username",user.username)
            startActivity(intent)
        }
    }

    private fun initView() {
        edtSearch = findViewById(R.id.edtSearch)
        btnSearch = findViewById(R.id.btnSearch)
        listView = findViewById(R.id.listview)

    }
}