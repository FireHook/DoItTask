package com.firehook.doittask.database

import android.arch.persistence.room.*
import com.firehook.doittask.network.model.Task

/**
 * Created by Vladyslav Bondar on 30.07.2019
 * Skype: diginital
 */

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskStore")
    fun getAll() : List<Task>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("DELETE FROM taskStore WHERE _id = :id")
    fun deleteById(id: Int)
}