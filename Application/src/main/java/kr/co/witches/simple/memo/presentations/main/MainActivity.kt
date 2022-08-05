package kr.co.witches.simple.memo.presentations.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kr.co.witches.simple.memo.databinding.ActivityMainBinding
import kr.co.witches.simple.memo.model.MemoItemVO
import kr.co.witches.simple.memo.model.MemoVO
import kr.co.witches.simple.memo.presentations.main.adapter.MainAdapter
import kr.co.witches.simple.memo.presentations.write.WriteActivity
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mDataReceiver: DataReceiver = DataReceiver()

    inner class DataReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                refreshData()

                refreshRecyclerView()
            }
        }
    }

    //  전체 데이터
    private var mData: ArrayList<MemoVO> = arrayListOf()
    private var mFilteredData: ArrayList<MemoItemVO> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
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
        LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(mDataReceiver)

        super.onDestroy()
    }

    private fun initViews() {
        LocalBroadcastManager.getInstance(applicationContext)
            .registerReceiver(mDataReceiver, IntentFilter("${packageName}.DATA_CHANGED"))

        binding.btnWrite.setOnClickListener {
            startWriteActivity(null)
        }
        binding.rvMemos.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)

        refreshRecyclerView()
    }

    private fun initData() {
        //  테스트 데이터
        val assetManager = resources.assets
        var source: InputStream? = null
        try {
            source = assetManager.open("memoList.json");
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val gson = Gson()
        val reader = InputStreamReader(source)

        mData = gson.fromJson(reader, genericType<ArrayList<MemoVO>>())

        refreshData()
    }

    private fun refreshData() {
        for (i in 0 until mData.size) {
            mFilteredData.add(mData[i].memos[0])
        }
    }

    private val mOnClickListener = View.OnClickListener {
        val position = it.tag.toString().toInt()
        println(">>> position : $position")
        //  상세 화면으로 이동
        startWriteActivity(position)
    }

    inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

    private fun refreshRecyclerView() {
        if (binding.rvMemos.adapter == null) {
            binding.rvMemos.adapter = MainAdapter(this, mFilteredData, mOnClickListener)
        }

        binding.rvMemos.adapter?.let { adapter ->
            if (adapter is MainAdapter) {
                adapter.data = mFilteredData
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun startWriteActivity(position: Int?) {
        val intent = Intent(this@MainActivity, WriteActivity::class.java)
        position?.let {
            intent.putExtra("MEMO", mData[it])
        }
        startActivity(intent)
    }
}