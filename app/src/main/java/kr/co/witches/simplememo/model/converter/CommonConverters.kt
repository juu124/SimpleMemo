package kr.co.witches.simplememo.model.converter

import androidx.room.TypeConverter
import kr.co.witches.simplememo.model.MemoLocationVO
import kr.co.witches.simplememo.model.MemoVO

class CommonConverters {

    @TypeConverter
    fun stringToMemoList(string: String): ArrayList<MemoVO> {
        return arrayListOf()
    }

    @TypeConverter
    fun memoListToString(memos: ArrayList<MemoVO>): String {
        return ""
    }

    /**
     * String 형태의 위치정보를 MemoLocationVO로 전환
     * @param string : 위치정보
     * @return : MemoLocationVO 타입으로 전환
     */
    @TypeConverter
    fun stringToLocation(string: String): MemoLocationVO? {
        //  Todo : 위치 정보 -> MemoLocationVO
        //  string -> split 특정 구분자 예시 '|'
        //  MemoLocationVO(0,1,2)
        return null
    }

    /**
     * MemoLocationVO 형태의 위치정보를 String으로 변환
     * @param location : 위치정보
     * @return : String 타입으로 전환
     */
    @TypeConverter
    fun locationToString(location: MemoLocationVO): String? {
        //  Todo : MemoLocationVO -> 위치 정보
        return "${location.latitude}|${location.longitude}|${location.altitude}"
    }

    //  Todo : 타임스탬프 <-> 날짜


    //  Todo : 사용여부 <-> String Y , N || int 0 , 1

}