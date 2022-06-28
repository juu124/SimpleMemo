package kr.co.witches.simplememo.data.database.helper

import kotlinx.coroutines.flow.Flow
import kr.co.witches.simplememo.model.MemoVO

interface MemoHelper {

    suspend fun insertMemo(memo: MemoVO)

    suspend fun deleteMemo(memo: MemoVO)

    fun getAll(): Flow<List<MemoVO>>

    suspend fun deleteAll()

}