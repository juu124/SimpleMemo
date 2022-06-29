package kr.co.witches.simplememo.ui.main;

import java.lang.System;

/**
 * MVVM 패턴 적용
 * -> 액티비티에서 처리 : 뷰
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0012\u0010\u0013\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0016"}, d2 = {"Lkr/co/witches/simplememo/ui/main/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lkr/co/witches/simplememo/databinding/ActivityMainBinding;", "mainViewModel", "Lkr/co/witches/simplememo/ui/main/MainViewModel;", "getMainViewModel", "()Lkr/co/witches/simplememo/ui/main/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "memoViewModel", "Lkr/co/witches/simplememo/data/database/viewmodel/MemoViewModel;", "getMemoViewModel", "()Lkr/co/witches/simplememo/data/database/viewmodel/MemoViewModel;", "memoViewModel$delegate", "addListeners", "", "addObservers", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private kr.co.witches.simplememo.databinding.ActivityMainBinding binding;
    private final kotlin.Lazy mainViewModel$delegate = null;
    private final kotlin.Lazy memoViewModel$delegate = null;
    
    public MainActivity() {
        super();
    }
    
    private final kr.co.witches.simplememo.ui.main.MainViewModel getMainViewModel() {
        return null;
    }
    
    private final kr.co.witches.simplememo.data.database.viewmodel.MemoViewModel getMemoViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void addObservers() {
    }
    
    private final void addListeners() {
    }
}