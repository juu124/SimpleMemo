package kr.co.witches.simplememo.model

enum class MemoContentType {
    A, B, C,
    /**
     * 텍스트
     */
    text

    /**
     * 이미지
     */
    , image

    /**
     * 지도
     */
    , map

}