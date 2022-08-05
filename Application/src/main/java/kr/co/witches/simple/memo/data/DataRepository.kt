package kr.co.witches.simple.memo.data

import android.app.Application
import kr.co.witches.simple.memo.model.MemoVO

class DataRepository(val application: Application) {

    fun getMemos(): ArrayList<MemoVO> {
        return arrayListOf()
    }

    fun insertMemo(memo: MemoVO) {

    }

    fun updateMemo(memo: MemoVO) {

    }

    fun deleteMemo(id: Int) {

    }
}