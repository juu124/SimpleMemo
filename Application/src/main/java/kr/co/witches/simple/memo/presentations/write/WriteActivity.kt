package kr.co.witches.simple.memo.presentations.write

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.witches.simple.memo.R
import kr.co.witches.simple.memo.databinding.ActivityWriteBinding
import kr.co.witches.simple.memo.model.MemoVO
import kr.co.witches.simple.memo.presentations.write.adapter.WriteAdapter

class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding

    var mDatum: MemoVO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()

        initViews()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun setData(data: MemoVO) {
        mDatum = data
    }

    private fun initViews() {
        binding.ibMemoPlus.setOnClickListener {
            if (binding.layoutMemoPlus.visibility == View.VISIBLE) {
                binding.layoutMemoPlus.visibility = View.GONE
                binding.ibMemoPlus.setImageResource(R.drawable.memo_plus)
            } else {
                binding.layoutMemoPlus.visibility = View.VISIBLE
                binding.ibMemoPlus.setImageResource(R.drawable.memo_close)
            }
        }

        binding.rvMemo.layoutManager =
            LinearLayoutManager(this@WriteActivity, RecyclerView.VERTICAL, false)
    }

    private fun initData() {
        mDatum = intent.getParcelableExtra<MemoVO>("MEMO")
        mDatum?.let {
            refreshRecyclerView()
        }
    }

    private fun refreshRecyclerView() {
        mDatum?.let { data ->
            if (binding.rvMemo.adapter == null) {
                binding.rvMemo.adapter = WriteAdapter(this, data.memos, mOnClickListener)
            }

            binding.rvMemo.adapter?.let { adapter ->
                if (adapter is WriteAdapter) {
                    adapter.data = data.memos
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    private val mOnClickListener = View.OnClickListener {
        val position = it.tag.toString().toInt()
        println(">>> position : $position")
        //  이미지는 이미지
        //  지도는 지도
        //  텍스트는 내용 수정
    }
}