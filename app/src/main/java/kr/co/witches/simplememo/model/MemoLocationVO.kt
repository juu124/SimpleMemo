package kr.co.witches.simplememo.model

data class MemoLocationVO(

    /**
     * 위도
     */
    val latitude: Double

    /**
     * 경도
     */
    , val longitude: Double

    /**
     * 고도
     */
    , val altitude: Double

)
