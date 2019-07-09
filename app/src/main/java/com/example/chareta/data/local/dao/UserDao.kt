package com.example.chareta.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chareta.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE  id = :id")
    fun getUserById(id: Long): LiveData<User>

    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("DELETE FROM users WHERE id = :userId")
    fun deleteUserById(userId: Long)

}