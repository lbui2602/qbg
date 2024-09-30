package com.map

import android.app.Application
import com.map.db.DatabaseHelper
import com.map.db.UserDao

class MyApplication : Application() {

    lateinit var dbHelper: DatabaseHelper
    lateinit var userDao: UserDao

    override fun onCreate() {
        super.onCreate()

        // Khởi tạo dbHelper và userDao một lần
        dbHelper = DatabaseHelper(this)
        userDao = UserDao(dbHelper)
    }
}
