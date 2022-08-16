package kr.co.witches.simple.memo

import kr.co.witches.simple.memo.model.MemoItemVO
import kr.co.witches.simple.memo.model.MemoVO

object AppVariables {

    /**
     * 전체 메모 데이터
     */
    var gMemos: ArrayList<MemoVO> = arrayListOf()
    var mFilteredMemos: ArrayList<MemoItemVO> = arrayListOf()
}