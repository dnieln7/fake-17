package com.dnieln7.fake17.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "tb_cats")
data class Cat(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: String,
    @ColumnInfo(name = "affection_level")
    @SerializedName("affection_level")
    var affectionLevel: Int,
    @ColumnInfo(name = "child_friendly")
    @SerializedName("child_friendly")
    var childFriendly: Int,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String,
    @ColumnInfo(name = "dog_friendly")
    @SerializedName("dog_friendly")
    var dogFriendly: Int,
    @ColumnInfo(name = "energy_level")
    @SerializedName("energy_level")
    var energyLevel: Int,
    @ColumnInfo(name = "hairless")
    @SerializedName("hairless")
    var hairless: Int,
    @ColumnInfo(name = "hypoallergenic")
    @SerializedName("hypoallergenic")
    var hypoallergenic: Int,
    @ColumnInfo(name = "image")
    @SerializedName("image")
    var catImage: CatImage? = CatImage(),
    @ColumnInfo(name = "indoor")
    @SerializedName("indoor")
    var indoor: Int?,
    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    var lifeSpan: String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String,
    @ColumnInfo(name = "origin")
    @SerializedName("origin")
    var origin: String,
    @ColumnInfo(name = "shedding_level")
    @SerializedName("shedding_level")
    var sheddingLevel: Int,
    @ColumnInfo(name = "social_needs")
    @SerializedName("social_needs")
    var socialNeeds: Int,
    @ColumnInfo(name = "stranger_friendly")
    @SerializedName("stranger_friendly")
    var strangerFriendly: Int,
    @ColumnInfo(name = "temperament")
    @SerializedName("temperament")
    var temperament: String,
    @ColumnInfo(name = "weight")
    @SerializedName("weight")
    var catWeight: CatWeight,
    @ColumnInfo(name = "wikipedia_url")
    @SerializedName("wikipedia_url")
    var wikipediaUrl: String?
):Serializable