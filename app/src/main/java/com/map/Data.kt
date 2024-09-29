package com.map

import android.os.Build
import androidx.annotation.RequiresApi
import com.map.models.User
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.format.DateTimeFormatter
import kotlin.random.Random

object Data {
    var username = ""
    val list = mutableListOf<User>()
    @RequiresApi(Build.VERSION_CODES.O)
    var date =LocalDate.now()
    val gender = listOf("Male", "Female", "Other").random()
    @RequiresApi(Build.VERSION_CODES.O)
    fun fakeData() : List<User>{
        list.add(User("quannh","123456","Nguyen Hong Quan","quan@gmail.com",date, gender))
        list.add(User("luongbd","123456","Bui Duc Luong","luong@gmail.com",date, gender))
        list.add(User("truongbx","123456","Bui Xuan Truong","truong@gmail.com",date, gender))
        list.add(User("thunn","123456","Nguyen Ngoc Thu","thu@gmail.com",date, gender))
        list.add(User("anhnp","123456","Nguyen Phuong Anh","quan@gmail.com",date, gender))
        list.add(User("tunt","123456","Nguyen Tuan Tu","tu@gmail.com",date, gender))
        list.add(User("datdt","123456","Dang Tran Dat","dat@gmail.com",date, gender))
        list.add(User("duongtd","123456","Ta Duc Duong","duong@gmail.com",date, gender))
        list.add(User("phonglt","123456","Luong The Phong","phong@gmail.com",date, gender))
        list.add(User("baonq","123456","Nguyen Quoc Bao","bao@gmail.com",date, gender))
        list.add(User("binhht","123456","Hoang Thanh Binh","binh@gmail.com",date, gender))
        return list
    }
    fun add(user:User){
        list.add(user)
    }
    fun update(username: String, userUpdate: User): Boolean {
        list.forEach { user ->
            if (user.username == username) {
                user.password = userUpdate.password
                user.fullname = userUpdate.fullname
                user.email = userUpdate.email
                return true
            }
        }
        return false
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(date: LocalDate): String {
        // Định dạng theo kiểu dd/MM/yyyy
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        // Trả về ngày đã định dạng
        return date.format(formatter)
    }
}