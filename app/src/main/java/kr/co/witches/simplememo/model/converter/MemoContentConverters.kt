package kr.co.witches.simplememo.model.converter

import androidx.room.TypeConverter
import kr.co.witches.simplememo.model.MemoContentVO
import kr.co.witches.simplememo.model.MemoLocationVO
import kr.co.witches.simplememo.model.MemoVO

class MemoContentConverters {

    //  Todo : - Gson 사용
    @TypeConverter
    fun stringToMemoList(string: String): ArrayList<MemoContentVO> {
        return arrayListOf()
    }

    @TypeConverter
    fun memoListToString(memos: ArrayList<MemoContentVO>): String {
        return ""
    }
}