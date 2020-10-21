package com.example.madlevel4task2

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "match_table")
data class Match (
    @ColumnInfo(name = "matchdate") var matchDate: Date,
    @ColumnInfo(name = "matchresult") var matchResult : Int,
    @ColumnInfo(name = "computermove") var computerMove : Int,
    @ColumnInfo(name = "playermove") var playerMove : Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)
