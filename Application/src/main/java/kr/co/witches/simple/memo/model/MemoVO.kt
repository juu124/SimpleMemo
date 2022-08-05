package kr.co.witches.simple.memo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemoVO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("memos")
    val memos: ArrayList<MemoItemVO>
) : Parcelable
