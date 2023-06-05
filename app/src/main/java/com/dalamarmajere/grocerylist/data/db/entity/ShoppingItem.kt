package com.dalamarmajere.grocerylist.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShoppingDB")
data class ShoppingItem(
    @ColumnInfo(name = "item_name")
    val name: String,
    @ColumnInfo(name = "item_amount")
    var amount: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)