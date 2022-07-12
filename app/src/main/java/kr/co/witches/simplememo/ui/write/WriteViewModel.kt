package kr.co.witches.simplememo.ui.write

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.witches.simplememo.data.database.repository.MemoRepository
import kr.co.witches.simplememo.model.MemoVO

class WriteViewModel : ViewModel() {

    private val _memo = MutableLiveData<MemoVO>()

    val memo: LiveData<MemoVO> = _memo
}