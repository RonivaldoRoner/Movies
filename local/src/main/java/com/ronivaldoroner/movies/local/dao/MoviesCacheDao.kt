package com.ronivaldoroner.movies.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ronivaldoroner.movies.local.base.CacheData
import com.ronivaldoroner.movies.local.base.TABLE_NAME

@Dao
interface MoviesCacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMoviesCache(data: CacheData)

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun getMoviesCache(id: String): CacheData?
}