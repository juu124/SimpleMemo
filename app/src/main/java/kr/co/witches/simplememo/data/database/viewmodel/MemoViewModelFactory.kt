package kr.co.witches.simplememo.data.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.witches.simplememo.data.database.repository.MemoRepository
import java.lang.IllegalArgumentException

class MemoViewModelFactory(private val repository: MemoRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MemoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}