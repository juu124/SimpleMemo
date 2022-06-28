package kr.co.witches.simplememo.data.database.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kr.co.witches.simplememo.data.database.MemoDatabase
import kr.co.witches.simplememo.data.database.dao.MemoDao
import kr.co.witches.simplememo.model.MemoVO

class MemoRepository(private val memoDao: MemoDao) {

    val allMemos: Flow<List<MemoVO>> = memoDao.getAll()

    @WorkerThread
    suspend fun insertMemo(memo: MemoVO) {
        memoDao.insertMemo(memo)
    }

    @WorkerThread
    suspend fun deleteMemo(memo: MemoVO) {
        memoDao.deleteMemo(memo)
    }

    @WorkerThread
    suspend fun deleteAll() {
        memoDao.deleteAll()
    }

}