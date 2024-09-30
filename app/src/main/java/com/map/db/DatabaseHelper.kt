package com.map.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "users.db"
        const val DATABASE_VERSION = 1
        const val TABLE_USER = "User"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_FULLNAME = "fullname"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_DOB = "dob"
        const val COLUMN_GENDER = "gender"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_USER (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USERNAME TEXT,
                $COLUMN_PASSWORD TEXT,
                $COLUMN_FULLNAME TEXT,
                $COLUMN_EMAIL TEXT,
                $COLUMN_DOB TEXT,
                $COLUMN_GENDER TEXT
            )
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }
}
