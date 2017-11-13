package com.fstyle.roompersistencelibraryexample

import android.app.Application
import com.fstyle.roompersistencelibraryexample.data.AppDatabase

/**
 * Created by daolq on 11/11/17.
 */

class RoomPersistenceApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    mAppDatabase = AppDatabase.createPersistentDatabase(this)
  }

  companion object {

    lateinit var mAppDatabase: AppDatabase
  }
}
