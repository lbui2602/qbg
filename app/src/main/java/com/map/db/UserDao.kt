package com.map.db

import android.content.ContentValues
import android.os.Build
import androidx.annotation.RequiresApi
import com.map.models.User
import java.time.LocalDate

class UserDao(private val dbHelper: DatabaseHelper) {

    fun addUser(user: User): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_USERNAME, user.username)
            put(DatabaseHelper.COLUMN_PASSWORD, user.password)
            put(DatabaseHelper.COLUMN_FULLNAME, user.fullname)
            put(DatabaseHelper.COLUMN_EMAIL, user.email)
            put(DatabaseHelper.COLUMN_DOB, user.dob.toString())
            put(DatabaseHelper.COLUMN_GENDER, user.gender)
        }
        return db.insert(DatabaseHelper.TABLE_USER, null, values)
    }
    fun getUserByUsername(username: String): User? {
        val db = dbHelper.readableDatabase
        var user: User? = null

        // Thực hiện truy vấn để lấy thông tin người dùng theo username
        val cursor = db.query(
            DatabaseHelper.TABLE_USER,  // Tên bảng
            null,  // Lấy tất cả các cột
            "${DatabaseHelper.COLUMN_USERNAME} = ?",  // Điều kiện WHERE
            arrayOf(username),  // Tham số điều kiện (giá trị username)
            null,  // Không nhóm các hàng
            null,  // Không có điều kiện HAVING
            null   // Không sắp xếp
        )

        if (cursor.moveToFirst()) {
            user = User(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)),
                username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD)),
                fullname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FULLNAME)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)),
                dob = LocalDate.parse(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DOB))),
                gender = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GENDER))
            )
        }
        cursor.close()
        return user
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fakeData() {
        val db = dbHelper.writableDatabase
        val date = LocalDate.of(2000, 1, 1) // Ngày sinh mặc định
        val gender = "Male" // Giới tính mặc định

        val users = listOf(
            User(username = "quannh", password = "123456", fullname = "Nguyen Hong Quan", email = "quan@gmail.com", dob = date, gender = gender),
            User(username = "luongbd", password = "123456", fullname = "Bui Duc Luong", email = "luong@gmail.com", dob = date, gender = gender),
            User(username = "truongbx", password = "123456", fullname = "Bui Xuan Truong", email = "truong@gmail.com", dob = date, gender = gender),
            User(username = "thunn", password = "123456", fullname = "Nguyen Ngoc Thu", email = "thu@gmail.com", dob = date, gender = gender),
            User(username = "anhnp", password = "123456", fullname = "Nguyen Phuong Anh", email = "anh@gmail.com", dob = date, gender = gender),
            User(username = "tunt", password = "123456", fullname = "Nguyen Tuan Tu", email = "tu@gmail.com", dob = date, gender = gender),
            User(username = "datdt", password = "123456", fullname = "Dang Tran Dat", email = "dat@gmail.com", dob = date, gender = gender),
            User(username = "duongtd", password = "123456", fullname = "Ta Duc Duong", email = "duong@gmail.com", dob = date, gender = gender),
            User(username = "phonglt", password = "123456", fullname = "Luong The Phong", email = "phong@gmail.com", dob = date, gender = gender),
            User(username = "baonq", password = "123456", fullname = "Nguyen Quoc Bao", email = "bao@gmail.com", dob = date, gender = gender),
            User(username = "binhht", password = "123456", fullname = "Hoang Thanh Binh", email = "binh@gmail.com", dob = date, gender = gender)
        )

        users.forEach { user ->
            val values = ContentValues().apply {
                put(DatabaseHelper.COLUMN_USERNAME, user.username)
                put(DatabaseHelper.COLUMN_PASSWORD, user.password)
                put(DatabaseHelper.COLUMN_FULLNAME, user.fullname)
                put(DatabaseHelper.COLUMN_EMAIL, user.email)
                put(DatabaseHelper.COLUMN_DOB, user.dob.toString())
                put(DatabaseHelper.COLUMN_GENDER, user.gender)
            }
            db.insert(DatabaseHelper.TABLE_USER, null, values)
        }
    }

    fun deleteUser(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete(DatabaseHelper.TABLE_USER, "${DatabaseHelper.COLUMN_ID} = ?", arrayOf(id.toString()))
    }

    fun updateUserByUserId(userId: Int, user: User): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_USERNAME, user.username)
            put(DatabaseHelper.COLUMN_PASSWORD, user.password)
            put(DatabaseHelper.COLUMN_FULLNAME, user.fullname)
            put(DatabaseHelper.COLUMN_EMAIL, user.email)
            put(DatabaseHelper.COLUMN_DOB, user.dob.toString())
            put(DatabaseHelper.COLUMN_GENDER, user.gender)
        }
        // Thực hiện cập nhật người dùng dựa trên userId
        return db.update(
            DatabaseHelper.TABLE_USER,
            values,
            "${DatabaseHelper.COLUMN_ID} = ?",  // Điều kiện WHERE là ID của user
            arrayOf(userId.toString())  // Tham số điều kiện là giá trị userId
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllUsers(): List<User> {
        val userList = mutableListOf<User>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_USER}", null)
        if (cursor.moveToFirst()) {
            do {
                val user = User(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)),
                    username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME)),
                    password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD)),
                    fullname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FULLNAME)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)),
                    dob = LocalDate.parse(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DOB))),
                    gender = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GENDER))
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return userList
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getListUserByName(username: String): List<User> {
        val userList = mutableListOf<User>()
        val db = dbHelper.readableDatabase

        // Thực hiện truy vấn với điều kiện WHERE theo username
        val cursor = db.query(
            DatabaseHelper.TABLE_USER,  // Tên bảng
            null,  // Lấy tất cả các cột
            "${DatabaseHelper.COLUMN_USERNAME} LIKE ?",  // Điều kiện WHERE
            arrayOf("%$username%"),  // Tham số điều kiện, tìm các username chứa chuỗi nhập vào
            null,  // Nhóm kết quả
            null,  // Điều kiện HAVING
            null   // Thứ tự sắp xếp
        )

        if (cursor.moveToFirst()) {
            do {
                val user = User(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)),
                    username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME)),
                    password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD)),
                    fullname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FULLNAME)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)),
                    dob = LocalDate.parse(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DOB))),
                    gender = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GENDER))
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return userList
    }

}
