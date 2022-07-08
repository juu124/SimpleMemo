package kr.co.witches.simplememo.data.database.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.witches.simplememo.data.database.repository.MemoRepository
import kr.co.witches.simplememo.model.MemoVO

class MemoViewModel(private val repository: MemoRepository): ViewModel() {

    fun insert(memo: MemoVO) = viewModelScope.launch {
        repository.insertMemo(memo)
    }

    fun delete(memo: MemoVO) = viewModelScope.launch {
        repository.deleteMemo(memo)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getAll(): LiveData<List<MemoVO>> {
        return repository.getAll().asLiveData()
    }

}