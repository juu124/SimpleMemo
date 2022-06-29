package kr.co.witches.simplememo.databinding;
import kr.co.witches.simplememo.R;
import kr.co.witches.simplememo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMainBindingImpl extends ActivityMainBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.btnAddMemo, 2);
    }
    // views
    @NonNull
    private final androidx.appcompat.widget.LinearLayoutCompat mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMainBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private ActivityMainBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.Button) bindings[2]
            , (android.widget.CheckBox) bindings[1]
            );
        this.btnFavorite.setTag(null);
        this.mboundView0 = (androidx.appcompat.widget.LinearLayoutCompat) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.mainViewModel == variableId) {
            setMainViewModel((kr.co.witches.simplememo.ui.main.MainViewModel) variable);
        }
        else if (BR.mainActivity == variableId) {
            setMainActivity((kr.co.witches.simplememo.ui.main.MainActivity) variable);
        }
        else if (BR.memoViewModel == variableId) {
            setMemoViewModel((kr.co.witches.simplememo.data.database.viewmodel.MemoViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setMainViewModel(@Nullable kr.co.witches.simplememo.ui.main.MainViewModel MainViewModel) {
        this.mMainViewModel = MainViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.mainViewModel);
        super.requestRebind();
    }
    public void setMainActivity(@Nullable kr.co.witches.simplememo.ui.main.MainActivity MainActivity) {
        this.mMainActivity = MainActivity;
    }
    public void setMemoViewModel(@Nullable kr.co.witches.simplememo.data.database.viewmodel.MemoViewModel MemoViewModel) {
        this.mMemoViewModel = MemoViewModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeMainViewModelIsFavorite((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeMainViewModelIsFavorite(androidx.lifecycle.LiveData<java.lang.Boolean> MainViewModelIsFavorite, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.Boolean mainViewModelIsFavoriteGetValue = null;
        kr.co.witches.simplememo.ui.main.MainViewModel mainViewModel = mMainViewModel;
        androidx.lifecycle.LiveData<java.lang.Boolean> mainViewModelIsFavorite = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxMainViewModelIsFavoriteGetValue = false;

        if ((dirtyFlags & 0x13L) != 0) {



                if (mainViewModel != null) {
                    // read mainViewModel.isFavorite()
                    mainViewModelIsFavorite = mainViewModel.isFavorite();
                }
                updateLiveDataRegistration(0, mainViewModelIsFavorite);


                if (mainViewModelIsFavorite != null) {
                    // read mainViewModel.isFavorite().getValue()
                    mainViewModelIsFavoriteGetValue = mainViewModelIsFavorite.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(mainViewModel.isFavorite().getValue())
                androidxDatabindingViewDataBindingSafeUnboxMainViewModelIsFavoriteGetValue = androidx.databinding.ViewDataBinding.safeUnbox(mainViewModelIsFavoriteGetValue);
        }
        // batch finished
        if ((dirtyFlags & 0x13L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.btnFavorite, androidxDatabindingViewDataBindingSafeUnboxMainViewModelIsFavoriteGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): mainViewModel.isFavorite()
        flag 1 (0x2L): mainViewModel
        flag 2 (0x3L): mainActivity
        flag 3 (0x4L): memoViewModel
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}