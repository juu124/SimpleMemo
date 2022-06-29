package kr.co.witches.simplememo.model.converter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0007J \u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2\u0006\u0010\n\u001a\u00020\u0004H\u0007\u00a8\u0006\u000b"}, d2 = {"Lkr/co/witches/simplememo/model/converter/MemoConverters;", "", "()V", "memoListToString", "", "memos", "Ljava/util/ArrayList;", "Lkr/co/witches/simplememo/model/MemoVO;", "Lkotlin/collections/ArrayList;", "stringToMemoList", "string", "app_debug"})
public final class MemoConverters {
    
    public MemoConverters() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.TypeConverter()
    public final java.util.ArrayList<kr.co.witches.simplememo.model.MemoVO> stringToMemoList(@org.jetbrains.annotations.NotNull()
    java.lang.String string) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.TypeConverter()
    public final java.lang.String memoListToString(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<kr.co.witches.simplememo.model.MemoVO> memos) {
        return null;
    }
}