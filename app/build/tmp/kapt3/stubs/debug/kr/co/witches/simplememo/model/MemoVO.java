package kr.co.witches.simplememo.model;

import java.lang.System;

@androidx.room.Entity(tableName = "tb_memo")
@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002BG\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00000\u0006j\b\u0012\u0004\u0012\u00020\u0000`\u0007\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0011J\u0019\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u00000\u0006j\b\u0012\u0004\u0012\u00020\u0000`\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0004H\u00c6\u0003J\t\u0010 \u001a\u00020\u0004H\u00c6\u0003J\t\u0010!\u001a\u00020\fH\u00c6\u0003J\\\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00000\u0006j\b\u0012\u0004\u0012\u00020\u0000`\u00072\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001\u00a2\u0006\u0002\u0010#J\t\u0010$\u001a\u00020\fH\u00d6\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010(H\u00d6\u0003J\t\u0010)\u001a\u00020\fH\u00d6\u0001J\t\u0010*\u001a\u00020+H\u00d6\u0001J\u0019\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\fH\u00d6\u0001R\u0016\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0015R.\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00000\u0006j\b\u0012\u0004\u0012\u00020\u0000`\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0016\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000fR\u0016\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000f\u00a8\u00061"}, d2 = {"Lkr/co/witches/simplememo/model/MemoVO;", "Landroid/os/Parcelable;", "()V", "id", "", "memo", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "regDate", "modDate", "delDate", "isUse", "", "(Ljava/lang/Long;Ljava/util/ArrayList;JJJI)V", "getDelDate", "()J", "getId", "()Ljava/lang/Long;", "setId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "()I", "getMemo", "()Ljava/util/ArrayList;", "setMemo", "(Ljava/util/ArrayList;)V", "getModDate", "getRegDate", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Long;Ljava/util/ArrayList;JJJI)Lkr/co/witches/simplememo/model/MemoVO;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_debug"})
public final class MemoVO implements android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    @androidx.room.PrimaryKey(autoGenerate = true)
    private java.lang.Long id;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "memo")
    private java.util.ArrayList<kr.co.witches.simplememo.model.MemoVO> memo;
    @androidx.room.ColumnInfo(name = "regDate")
    private final long regDate = 0L;
    @androidx.room.ColumnInfo(name = "modDate")
    private final long modDate = 0L;
    @androidx.room.ColumnInfo(name = "delDate")
    private final long delDate = 0L;
    @androidx.room.ColumnInfo(name = "isUse")
    private final int isUse = 0;
    public static final android.os.Parcelable.Creator<kr.co.witches.simplememo.model.MemoVO> CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final kr.co.witches.simplememo.model.MemoVO copy(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<kr.co.witches.simplememo.model.MemoVO> memo, long regDate, long modDate, long delDate, int isUse) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public MemoVO(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<kr.co.witches.simplememo.model.MemoVO> memo, long regDate, long modDate, long delDate, int isUse) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
    
    public final void setId(@org.jetbrains.annotations.Nullable()
    java.lang.Long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<kr.co.witches.simplememo.model.MemoVO> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<kr.co.witches.simplememo.model.MemoVO> getMemo() {
        return null;
    }
    
    public final void setMemo(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<kr.co.witches.simplememo.model.MemoVO> p0) {
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long getRegDate() {
        return 0L;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final long getModDate() {
        return 0L;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final long getDelDate() {
        return 0L;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int isUse() {
        return 0;
    }
    
    public MemoVO() {
        super();
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<kr.co.witches.simplememo.model.MemoVO> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final kr.co.witches.simplememo.model.MemoVO createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final kr.co.witches.simplememo.model.MemoVO[] newArray(int size) {
            return null;
        }
    }
}