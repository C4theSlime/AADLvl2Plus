package com.example.madlevel4task2.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.madlevel4task2.Match

@Database(entities = [Match::class], version = 1, exportSchema = false)
abstract class MatchListRoomDatabase : RoomDatabase() {

    abstract fun matchDao(): MatchDao

    companion object {
        private const val DATABASE_NAME = "SHOPPING_LIST_DATABASE"

        @Volatile
        private var matchListRoomDatabaseInstance: MatchListRoomDatabase? = null

        fun getDatabase(context: Context): MatchListRoomDatabase? {
            if (matchListRoomDatabaseInstance == null) {
                synchronized(MatchListRoomDatabase::class.java) {
                    if (matchListRoomDatabaseInstance == null) {
                        matchListRoomDatabaseInstance =
                            Room.databaseBuilder(
                                context.applicationContext,
                                MatchListRoomDatabase::class.java, DATABASE_NAME
                            ).build()
                    }
                }
            }
            return matchListRoomDatabaseInstance
        }
    }
}

