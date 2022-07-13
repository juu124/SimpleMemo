package kr.co.witches.simplememo.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import kr.co.witches.simplememo.AppApplication
import kr.co.witches.simplememo.R
import kr.co.witches.simplememo.data.database.viewmodel.MemoViewModel
import kr.co.witches.simplememo.data.database.viewmodel.MemoViewModelFactory
import kr.co.witches.simplememo.databinding.ActivityMainBinding
import kr.co.witches.simplememo.ui.write.WriteActivity
import java.util.jar.Manifest

/**
 * MVVM 패턴 적용
 *  -> 액티비티에서 처리 : 뷰
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()
    private val memoViewModel: MemoViewModel by viewModels {
        MemoViewModelFactory((application as AppApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.mainActivity = this
        binding.mainViewModel = mainViewModel
        binding.memoViewModel = memoViewModel

        addObservers()

        addListeners()

        //메모추가 버튼
        val btnAddMemo: Button = findViewById(R.id.btn_main_add_memo)
        btnAddMemo.setOnClickListener{
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }


    }

    private fun addObservers() {
        //  메인뷰모델의 메모 데이터가 변경된 경우
        memoViewModel.getAll().observe(this) { memos ->
            println(">>> memos : ${memos.size}")
            //  리사이클러뷰 갱신
        }

        binding.btnFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            println(">>> setOnCheckedChangeListener : $isChecked")
            mainViewModel.setFavorite(isChecked)
        }
    }

    private fun addListeners() {

    }
}