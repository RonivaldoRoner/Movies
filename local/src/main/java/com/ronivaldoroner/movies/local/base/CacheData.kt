package com.ronivaldoroner.movies.local.base

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName


@Entity(tableName = TABLE_NAME, indices = [Index(value = ["id"], unique = true)])
data class CacheData(
    @Ignore
    val sessionId: SessionId,
    @SerialName("data")
    @ColumnInfo(name = "data")
    val data: String
){
    @PrimaryKey
    @SerialName("id")
    val id: String = sessionId.id
}


data class SessionId(
    val userId: String,
    val featureId: String,
) {
    val id: String = "${userId}-${featureId}"
}

const val TABLE_NAME = "movies_cache"