package com.fstyle.roompersistencelibraryexample.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by daolq on 11/10/17.
 */

@Entity(tableName = "counter")
data class Counter(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String = "",
    val position: Long = 0,
    val count: Long = 0
)
