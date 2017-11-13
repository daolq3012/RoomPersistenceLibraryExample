package com.fstyle.roompersistencelibraryexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fstyle.roompersistencelibraryexample.data.Counter
import com.fstyle.roompersistencelibraryexample.data.CounterDao
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import java.util.Random

class MainActivity : AppCompatActivity(), AnkoLogger {

  private lateinit var counterDao: CounterDao

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    counterDao = RoomPersistenceApplication.mAppDatabase.counterModel()

    addButton.setOnClickListener({ _ ->
      Completable.fromCallable {
        counterDao.insertOrUpdate(Counter(Random().nextLong(), "abc"))
      }.subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread()).subscribe(
          {
            info("Data inserted")
          }, { e ->
        error(e)
      })
    })

    deleteButton.setOnClickListener({ _ ->
      Completable.fromCallable { counterDao.deleteAllCounters() }.subscribeOn(
          Schedulers.io()).observeOn(
          AndroidSchedulers.mainThread()).subscribe(
          { info("All data deleted") },
          { e -> error(e) })
    })

    counterDao.count().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ i -> resultLabel.text = "$i" }, { e -> resultLabel.text = e.message })
  }
}
