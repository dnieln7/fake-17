package com.dnieln7.fake17.domain

data class User(
    val id: Int,
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val hobbies: List<String>,
    val rol: String
)