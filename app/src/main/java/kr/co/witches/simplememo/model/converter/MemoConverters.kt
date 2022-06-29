package kr.co.witches.simplememo.model.converter

import androidx.room.TypeConverter
import kr.co.witches.simplememo.model.MemoLocationVO
import kr.co.witches.simplememo.model.MemoVO

class MemoConverters {

    //  Todo : - Gson 사용
    @TypeConverter
    fun stringToMemoList(string: String): ArrayList<MemoVO> {
        return arrayListOf()
    }

    @TypeConverter
    fun memoListToString(memos: ArrayList<MemoVO>): String {
        return ""
    }
}