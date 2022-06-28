package kr.co.witches.simplememo.model

data class MemoContentVO(
    /**
     * 메모 컨텐츠 유형
     * T : 텍스트
     * I : 이미지
     * M : 지도
     */
    val type: MemoContentType
    /**
     * 내용
     * T : char[]
     * I : local image path || binary data
     * M : {kr.co.witches.simplememo.model.MemoLocationVO}
     */
    , val content: String
)
