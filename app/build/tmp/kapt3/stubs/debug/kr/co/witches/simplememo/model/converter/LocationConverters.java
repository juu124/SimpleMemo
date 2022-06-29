package kr.co.witches.simplememo.model.converter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007\u00a8\u0006\t"}, d2 = {"Lkr/co/witches/simplememo/model/converter/LocationConverters;", "", "()V", "locationToString", "", "location", "Lkr/co/witches/simplememo/model/MemoLocationVO;", "stringToLocation", "string", "app_debug"})
public final class LocationConverters {
    
    public LocationConverters() {
        super();
    }
    
    /**
     * String 형태의 위치정보를 MemoLocationVO로 전환
     * @param string : 위치정보
     * @return : MemoLocationVO 타입으로 전환
     */
    @org.jetbrains.annotations.Nullable()
    @androidx.room.TypeConverter()
    public final kr.co.witches.simplememo.model.MemoLocationVO stringToLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String string) {
        return null;
    }
    
    /**
     * MemoLocationVO 형태의 위치정보를 String으로 변환
     * @param location : 위치정보
     * @return : String 타입으로 전환
     */
    @org.jetbrains.annotations.Nullable()
    @androidx.room.TypeConverter()
    public final java.lang.String locationToString(@org.jetbrains.annotations.NotNull()
    kr.co.witches.simplememo.model.MemoLocationVO location) {
        return null;
    }
}