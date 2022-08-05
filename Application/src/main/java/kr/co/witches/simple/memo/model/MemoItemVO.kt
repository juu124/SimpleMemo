package kr.co.witches.simple.memo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemoItemVO(
    @SerializedName("type")
    val type: String,
    @SerializedName("contents")
    val contents: String
) : Parcelable
