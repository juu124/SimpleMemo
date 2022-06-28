package kr.co.witches.simplememo.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kr.co.witches.simplememo.AppApplication
import kr.co.witches.simplememo.R
import kr.co.witches.simplememo.data.database.viewmodel.MemoViewModel
import kr.co.witches.simplememo.data.database.viewmodel.MemoViewModelFactory
import kr.co.witches.simplememo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val memoViewModel: MemoViewModel by viewModels {
        MemoViewModelFactory((application as AppApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        memoViewModel.memos().observe(this, Observer { memos ->
            println(">>> memos : ${memos.size}")
        })

    }

}