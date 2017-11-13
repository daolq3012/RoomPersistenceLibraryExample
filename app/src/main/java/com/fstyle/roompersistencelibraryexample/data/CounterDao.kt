package com.fstyle.roompersistencelibraryexample.data

import android.arch.persistence.room.*
import io.reactivex.*

/**
 * Created by daolq on 11/11/17.
 */

@Dao
interface CounterDao {

  @Query("SELECT COUNT(*) FROM counter")
  fun count(): Flowable<Int>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrUpdate(vararg counters: Counter)

  @Query("DELETE FROM counter")
  fun deleteAllCounters()
}
