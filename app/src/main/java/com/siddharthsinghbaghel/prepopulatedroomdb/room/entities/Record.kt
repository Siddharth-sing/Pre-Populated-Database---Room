package com.siddharthsinghbaghel.prepopulatedroomdb.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record")
data class Record(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val done: Boolean,
    val undone: Boolean,
    val bookmark: Boolean
    )