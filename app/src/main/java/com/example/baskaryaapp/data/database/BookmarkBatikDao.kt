package com.example.baskaryaapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookmarkBatikDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(bookmarkBatik: BookmarkBatik)

    @Update
    fun update(bookmarkBatik: BookmarkBatik)

    @Query("DELETE from bookmarkBatik WHERE bookmarkBatik.id = :id")
    suspend fun delete(id: Int): Int

    @Query("SELECT * from bookmarkBatik ORDER BY title ASC")
    fun getAllBookmarkBatik(): LiveData<List<BookmarkBatik>>

    @Query("SELECT count(*) FROM BookmarkBatik WHERE bookmarkBatik.id = :id")
    fun getBookmarkBatikByTitle(id: Int): Int
}