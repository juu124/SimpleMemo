package kr.co.witches.simplememo.data.database.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kr.co.witches.simplememo.data.database.dao.MemoDao
import kr.co.witches.simplememo.model.MemoVO

class MemoRepository(private val memoDao: MemoDao) {

    @WorkerThread
    suspend fun insertMemo(memo: MemoVO) {
        memoDao.insertMemo(memo)
    }

    @WorkerThread
    suspend fun deleteMemo(memo: MemoVO) {
        memoDao.deleteMemo(memo)
    }

    fun getAll(): Flow<List<MemoVO>> {
        return memoDao.getAll()
    }

    @WorkerThread
    suspend fun deleteAll() {
        memoDao.deleteAll()
    }

}