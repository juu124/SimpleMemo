package kr.co.witches.simplememo.model

import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tb_memo")
data class MemoVO(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "memo") var memo: ArrayList<MemoVO>,
    @ColumnInfo(name = "regDate") val regDate: Long,
    @ColumnInfo(name = "modDate") val modDate: Long,
    @ColumnInfo(name = "delDate") val delDate: Long,
    @ColumnInfo(name = "isUse") val isUse: Int
): Parcelable {
    constructor(): this(null, arrayListOf(), 0, 0, 0, 0)
}
