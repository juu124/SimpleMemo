package kr.co.witches.simplememo.ui.main

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.co.witches.simplememo.AppApplication
import kr.co.witches.simplememo.model.MemoVO

class MainViewModel(private val application: AppApplication) : AndroidViewModel(application) {

    //  MainActivity 에서 MainViewModel 데이터를 접근할 경우 필요
    private val _data = MutableLiveData<List<MemoVO>>()
    val data: LiveData<List<MemoVO>> = _data

    private fun getAllData() {
        //  전체 데이터
        //  application.repository.allMemos
    }

    private fun getFavoriteData() {
        //  필터링 데이터
        //  application.repository.allMemos
    }

    /**
     * 즐겨찾기 버튼 상태
     */
    var isFavorite = false
    fun setFavorite() {
        isFavorite = !isFavorite
        if (isFavorite) {
            getFavoriteData()
        } else {
            getAllData()
        }
    }

}