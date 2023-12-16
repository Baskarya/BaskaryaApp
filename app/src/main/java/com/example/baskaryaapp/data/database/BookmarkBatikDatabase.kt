package com.example.baskaryaapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookmarkBatik::class], version = 1)
abstract class BookmarkBatikDatabase : RoomDatabase() {

    abstract fun bookmarkBatikDao(): BookmarkBatikDao

    companion object {
        @Volatile
        private var INSTANCE: BookmarkBatikDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): BookmarkBatikDatabase {
            if (INSTANCE == null) {
                synchronized(BookmarkBatikDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        BookmarkBatikDatabase::class.java, "bookmark_batik_database")
                        .build()
                }
            }
            return INSTANCE as BookmarkBatikDatabase
        }
    }
}