package com.example.sneakerapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartItem::class],version = 1,exportSchema = false)
abstract class MyDatabase:RoomDatabase() {

    abstract fun sneakerDao(): SneakerDao

}