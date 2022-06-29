package kr.co.witches.simplememo.model.converter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007\u00a8\u0006\t"}, d2 = {"Lkr/co/witches/simplememo/model/converter/TimeStampConverters;", "", "()V", "dateStringToTimestamp", "", "string", "", "timestampToDateString", "timestamp", "app_debug"})
public final class TimeStampConverters {
    
    public TimeStampConverters() {
        super();
    }
    
    @androidx.room.TypeConverter()
    public final long dateStringToTimestamp(@org.jetbrains.annotations.NotNull()
    java.lang.String string) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.TypeConverter()
    public final java.lang.String timestampToDateString(long timestamp) {
        return null;
    }
}