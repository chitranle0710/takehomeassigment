package com.example.tymextakehomeapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tymextakehomeapp.data.local.entity.UserEntity

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: UserEntity)

    @Query("SELECT * FROM users ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM users")
    suspend fun clearAll()

    @Query("SELECT * FROM Users WHERE login = :id")
    suspend fun getUserById(id: Int): UserEntity?
}