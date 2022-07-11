package kr.co.witches.simplememo.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import kr.co.witches.simplememo.AppApplication
import kr.co.witches.simplememo.R
import kr.co.witches.simplememo.data.database.viewmodel.MemoViewModel
import kr.co.witches.simplememo.data.database.viewmodel.MemoViewModelFactory
import kr.co.witches.simplememo.databinding.ActivityMainBinding
import kr.co.witches.simplememo.util.OnSingleClickListener

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

        //  Todo : run, let, also, apply, with
        //  Todo : optional(?)
        binding.run {
            lifecycleOwner = this@MainActivity
        }

        //  Todo : let
        binding.let {
            it.mainActivity = this
        }
        //  Todo : also
        binding.also {
            it.mainViewModel = mainViewModel
        }

        //  Todo : apply
        binding.apply {
            this.memoViewModel = memoViewModel
        }

        //  Todo: with
        with(binding) {

        }

        addObservers()

        addListeners()

        //  Todo : - View Binding 으로 사용하기
        //  Todo : - 공통 성격의 함수에서 처리 할 것
//        val btnAddMemo: Button = findViewById(R.id.btn_main_add_memo)
//        btnAddMemo.setOnClickListener{
//            val intent = Intent(this, AddMemo::class.java)
//            startActivity(intent)
//        }
    }

    private fun addObservers() {
        memoViewModel.getAll().observe(this) { memos ->
            //  Todo : - RecyclerView Binding Adpdater 로 데이터 처리 할것
        }
    }

    private fun addListeners() {
        //  메모 추가 버튼
        binding.btnMainAddMemo.setOnClickListener(object : OnSingleClickListener() {

            override fun onSingleClick(v: View?) {
                val intent = Intent(this@MainActivity, AddMemo::class.java)
                startActivity(intent)
            }
        })

        //  즐겨찾기 애니메이션 처리
        binding.lavFavorite.setOnClickListener(object : OnSingleClickListener() {

            override fun onSingleClick(v: View?) {
                runOnUiThread { binding.lavFavorite.playAnimation() }
            }

        })
    }

}