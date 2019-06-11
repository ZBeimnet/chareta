package com.example.chareta.ServiceLocater

import android.content.Context
import android.system.Os.close
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.example.chareta.repository.ItemRepository
import kotlinx.coroutines.runBlocking

object ServiceLocator {

    private val lock = Any()
    private var database: CharetaDatabase? = null
    @Volatile var itemsRepository: ItemRepository? = null
        @VisibleForTesting set

    fun provideItemsRepository(context: Context): ItemRepository {
        synchronized(this) {
            return itemsRepository ?:
            itemsRepository ?: createItemsRepository(context)
        }
    }

    private fun createItemsRepository(context: Context): ItemRepository {
        database = Room.databaseBuilder(context.applicationContext,
            CharetaDatabase::class.java, "Tasks.db")
            .build()


    }

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            runBlocking {
                itemsRepository.deleteAll()
            }
            // Clear all data to avoid test pollution.
            database?.apply {
                clearAllTables()
                close()
            }
            ItemRepository = null
        }
    }
}
