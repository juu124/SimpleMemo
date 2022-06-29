package kr.co.witches.simplememo.data.database.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\u0006J\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lkr/co/witches/simplememo/data/database/viewmodel/MemoViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lkr/co/witches/simplememo/data/database/repository/MemoRepository;", "(Lkr/co/witches/simplememo/data/database/repository/MemoRepository;)V", "delete", "Lkotlinx/coroutines/Job;", "memo", "Lkr/co/witches/simplememo/model/MemoVO;", "deleteAll", "getAll", "Landroidx/lifecycle/LiveData;", "", "insert", "app_debug"})
public final class MemoViewModel extends androidx.lifecycle.ViewModel {
    private final kr.co.witches.simplememo.data.database.repository.MemoRepository repository = null;
    
    public MemoViewModel(@org.jetbrains.annotations.NotNull()
    kr.co.witches.simplememo.data.database.repository.MemoRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insert(@org.jetbrains.annotations.NotNull()
    kr.co.witches.simplememo.model.MemoVO memo) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job delete(@org.jetbrains.annotations.NotNull()
    kr.co.witches.simplememo.model.MemoVO memo) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteAll() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kr.co.witches.simplememo.model.MemoVO>> getAll() {
        return null;
    }
}