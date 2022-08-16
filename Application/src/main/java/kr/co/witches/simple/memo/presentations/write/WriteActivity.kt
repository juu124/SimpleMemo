package kr.co.witches.simple.memo.presentations.write

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.witches.simple.memo.R
import kr.co.witches.simple.memo.databinding.ActivityWriteBinding
import kr.co.witches.simple.memo.model.MemoItemVO
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
                Log.d("TAG", "initViews >>> visibility == View.GONE")
                binding.ibMemoPlus.setImageResource(R.drawable.memo_plus)
            } else {
                // +를 눌렀을 때 사진, 영상, 이미지가 올라간다.
                binding.layoutMemoPlus.visibility = View.VISIBLE
                Log.d("TAG", "initViews >>> visibility = View.VISIBLE")
                binding.ibMemoPlus.setImageResource(R.drawable.memo_close)
            }
        }

        // 메모 추가
        binding.ibMemoSend.setOnClickListener{
            Log.d("TAG", "ibMemoSend")
            if(binding.etMemoMessage.text.isNotBlank()){
                val content = ArrayList<MemoItemVO>()
            }
        }

        binding.rvMemo.layoutManager =
            LinearLayoutManager(this@WriteActivity, RecyclerView.VERTICAL, false)
    }

    private fun initData() {
        // MainActivity에서 putExtra("MEMO")했던 것을 여기서 받는다
        mDatum = intent.getParcelableExtra<MemoVO>("MEMO")

        // add+만 누르면 아래 WriteActivity로그만 뜬다.
        Log.d("TAG", "WriteActivity")

        // MainActivy에서 글을 클릭했을 때
        mDatum?.let {
            Log.d("TAG", "initData")
            refreshRecyclerView()
        }
    }

    private fun refreshRecyclerView() {
        Log.d("TAG", "refreshRecyclerView")
        mDatum?.let { data ->
            if (binding.rvMemo.adapter == null) {
                binding.rvMemo.adapter = WriteAdapter(this, data.memos, mOnClickListener)
            }

            binding.rvMemo.adapter?.let { adapter ->
                // adapter가 WrtieAdapter인가요?
                Log.d("TAG", "refreshRecyclerView >>> rvMemoadapter")
                if (adapter is WriteAdapter) {
                    // adapter.data는 이미 저장된 data.memos와 같다
                    adapter.data = data.memos
                    Log.d("TAG", "refreshRecyclerView >>> adapter is WriteAdapter")
                }
                //리스트 업데이트(리스크 크기 변경, 아이템 변경)
                adapter.notifyDataSetChanged()
                Log.d("TAG", "refreshRecyclerView >>> adapter.notifyDataSetChanged")
            }
        }
    }

    private val mOnClickListener = View.OnClickListener {
        val position = it.tag.toString().toInt()
        println(">>> position : $position")
        Log.d("TAG", "mOnClickListener")
    }
}

