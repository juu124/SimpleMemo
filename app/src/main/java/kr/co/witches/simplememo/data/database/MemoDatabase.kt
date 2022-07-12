package kr.co.witches.simplememo.data.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kr.co.witches.simplememo.data.database.dao.MemoDao
import kr.co.witches.simplememo.model.MemoVO
import kr.co.witches.simplememo.model.converter.LocationConverters
import kr.co.witches.simplememo.model.converter.MemoContentConverters
import kr.co.witches.simplememo.model.converter.MemoConverters
import kr.co.witches.simplememo.model.converter.TimeStampConverters

@Database(version = 1, exportSchema = false, entities = [MemoVO::class])
@TypeConverters(value = [LocationConverters::class, MemoConverters::class, MemoContentConverters::class, TimeStampConverters::class])
abstract class MemoDatabase : RoomDatabase() {

    abstract fun memoDao(): MemoDao

    private class MemoDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var memoDao = database.memoDao()
                    memoDao.deleteAll()
                }
            }
        }

    }

    companion object {

        private var INSTANCE: MemoDatabase? = null

        @Synchronized
        fun getDatabase(context: Context, scope: CoroutineScope): MemoDatabase? {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemoDatabase::class.java,
                    "databaseName"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(MemoDatabaseCallback(scope))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}