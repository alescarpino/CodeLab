package com.example.vera100.PopVote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.util.Date

@Entity(tableName = "films")
data class Film(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "comment")
    val comment: String,

    @ColumnInfo(name = "rating")
    val rating: Double, // Bewertung von 0.0 bis 5.0

    @ColumnInfo(name = "date_added")
    val dateAdded: Date = Date(),

    @ColumnInfo(name = "poster_data", typeAffinity = ColumnInfo.BLOB)
    val posterData: ByteArray? = null,

    @ColumnInfo(name = "folder_id")
    val folderId: Int? = null
)