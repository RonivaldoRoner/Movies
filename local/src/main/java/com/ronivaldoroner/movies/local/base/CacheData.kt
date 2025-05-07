package com.ronivaldoroner.movies.local.base

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = TABLE_NAME, indices = [Index(value = ["id"], unique = true)])
data class CacheData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "data")
    val data: String
)


const val TABLE_NAME = "movies_cache"