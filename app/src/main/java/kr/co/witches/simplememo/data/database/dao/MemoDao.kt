package kr.co.witches.simplememo.data.database.dao

import androidx.room.*
import kr.co.witches.simplememo.model.MemoVO
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemo(stepCount: MemoVO)

    @Delete
    suspend fun deleteMemo(stepCount: MemoVO)

    @Query("SELECT * FROM tb_memo ORDER BY id;")
    fun getAll(): Flow<List<MemoVO>>

    @Query("DELETE FROM tb_memo;")
    suspend fun deleteAll()

}