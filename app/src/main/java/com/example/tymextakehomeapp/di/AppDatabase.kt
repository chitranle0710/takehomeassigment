package com.example.tymextakehomeapp.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tymextakehomeapp.data.local.dao.UserDAO
import com.example.tymextakehomeapp.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO
}