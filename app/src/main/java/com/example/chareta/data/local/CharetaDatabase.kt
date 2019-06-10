package com.example.chareta.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chareta.data.local.dao.BidDao
import com.example.chareta.data.local.dao.ItemDao
import com.example.chareta.data.local.dao.UserDao
import com.example.chareta.data.local.model.Bid
import com.example.chareta.data.local.model.Item
import com.example.chareta.data.local.model.User

@Database(entities = arrayOf(User::class, Item::class, Bid::class), version = 1, exportSchema = false)
abstract class CharetaDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun itemDao(): ItemDao
    abstract fun bidDao(): BidDao

    companion object {

        @Volatile
        private var INSTANCE: CharetaDatabase? = null

        fun getDatabase(context: Context): CharetaDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharetaDatabase::class.java, "chareta_database"
                ).build()

                INSTANCE = instance
                return instance
            }

        }
    }
}

