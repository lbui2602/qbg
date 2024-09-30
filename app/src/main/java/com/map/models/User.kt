package com.map.models

import java.time.LocalDate

data class User(
    val id: Int? = 0,
    val username: String,
    var password: String,
    var fullname: String,
    var email: String,
    var dob: LocalDate,
    var gender: String
)
