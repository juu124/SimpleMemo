package kr.co.witches.simplememo.model.converter

import androidx.room.TypeConverter
import kr.co.witches.simplememo.model.MemoVO

class TimeStampConverters {

    @TypeConverter
    fun dateStringToTimestamp(string: String): Long {
        //  Todo - 내용 작성
        return 0
    }

    @TypeConverter
    fun timestampToDateString(timestamp: Long): String {
        return ""
    }
}