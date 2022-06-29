package kr.co.witches.simplememo.ui.main

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.witches.simplememo.data.database.repository.MemoRepository
import kr.co.witches.simplememo.model.MemoVO

class MainViewModel : ViewModel() {

//    private val _memos = MutableLiveData<List<MemoVO>>()
//    val memos: LiveData<List<MemoVO>> = _memos

    /**
     * 즐겨찾기 버튼 상태
     */
    private val _isFavorite = MutableLiveData<Boolean>()
    var isFavorite: LiveData<Boolean> = _isFavorite
    fun setFavorite(isChecked: Boolean) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            _isFavorite.value = isChecked
        } else {
            _isFavorite.postValue(isChecked)
        }
    }

}