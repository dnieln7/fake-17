package com.dnieln7.fake17.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tb_users")
data class User(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int,
    @ColumnInfo(name = "name")
    @SerializedName("nombre")
    var name: String,
    @ColumnInfo(name = "last_name")
    @SerializedName("apellidos")
    var lastName: String,
    @ColumnInfo(name = "email")
    @SerializedName("email")
    var email: String,
    @Ignore
    @SerializedName("password")
    val password: String,
    @ColumnInfo(name = "hobbies")
    @SerializedName("intereses")
    var hobbies: List<String>,
    @ColumnInfo(name = "role")
    @SerializedName("rol")
    var role: String
) {
    constructor(): this(0, "", "", "", "", emptyList(), "")
}