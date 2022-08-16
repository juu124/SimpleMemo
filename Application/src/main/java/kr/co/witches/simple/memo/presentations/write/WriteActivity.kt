package kr.co.witches.simple.memo.presentations.write

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.google.android.gms.location.FusedLocationProviderApi
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.witches.simple.memo.AppVariables
import kr.co.witches.simple.memo.R
import kr.co.witches.simple.memo.databinding.ActivityWriteBinding
import kr.co.witches.simple.memo.model.MemoItemVO
import kr.co.witches.simple.memo.model.MemoVO
import kr.co.witches.simple.memo.presentations.base.OnPermissionChecked
import kr.co.witches.simple.memo.presentations.write.adapter.WriteAdapter
import kr.co.witches.simple.memo.util.CommonUtil
import java.util.ArrayList

class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding

    var mDatum: MemoVO? = null
    var mMemoIndex: Int = -1
    var mSelectedIndex: Int = -1

    private var mOnPermissionChecked: OnPermissionChecked? = null

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

    override fun onBackPressed() {
        //super.onBackPressed()
        if ((mDatum?.memos?.size ?: 0) > 0) {
            if (mMemoIndex != -1) {
                //  수정
                mDatum?.let {
                    AppVariables.gMemos[mMemoIndex] = it
                }
            }
            else {
                //  추가
                mDatum?.let {
                    AppVariables.gMemos.add(AppVariables.gMemos.size, it)
                }
            }
        }
        else {
            if (mMemoIndex != -1) {
                AppVariables.gMemos.removeAt(mMemoIndex)
            }
        }

        val intent = Intent("${packageName}.DATA_CHANGED")
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)

        finish()
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

        //  텍스트 입력
        binding.ibMemoSend.setOnClickListener {
            mDatum?.let { memo ->
                val result = binding.etMemoMessage.text.toString()
                if (mSelectedIndex == -1) {
                    if (result != "") {
                        memo.memos.add(MemoItemVO("A", binding.etMemoMessage.text.toString()))
                    }
                    else {

                    }
                }
                else {
                    if (result != "") {
                        memo.memos[mSelectedIndex].contents = binding.etMemoMessage.text.toString()
                    }
                    else {
                        memo.memos.removeAt(mSelectedIndex)
                    }
                }
            }
            //  초기화
            mSelectedIndex = -1
            binding.etMemoMessage.setText("")
            refreshRecyclerView()
        }

        binding.ibMemoPic.setOnClickListener {
            openPhoto()
        }

        binding.ibChatVideo.setOnClickListener {
            Toast.makeText(this@WriteActivity, "준비중입니다.", Toast.LENGTH_SHORT).show()
        }

        binding.ibMemoMap.setOnClickListener {
            findCurrentLocation()
        }
    }

    private fun initData() {
        mMemoIndex = intent.getIntExtra("MEMO_INDEX", -1)
        mDatum = intent.getParcelableExtra<MemoVO>("MEMO") ?: MemoVO(-1, arrayListOf())
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
        mSelectedIndex = it.tag.toString().toInt()
        if (mSelectedIndex != -1) {
            if (mDatum?.memos?.get(mSelectedIndex)?.type == "A") {
                editText()
            }
            else if (mDatum?.memos?.get(mSelectedIndex)?.type == "B") {
                editPhoto()
            }
            else if (mDatum?.memos?.get(mSelectedIndex)?.type == "C") {
                editMap()
            }
        }
    }

    private fun editText() {
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.root, 0)

        if (mSelectedIndex != -1) {
            binding.etMemoMessage.setText(mDatum?.memos?.get(mSelectedIndex)?.contents ?: "")
            binding.etMemoMessage.setSelection(binding.etMemoMessage.text.length)
        }
    }

    private val mPhotoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    //  데이터 삭제
                    CoroutineScope(Dispatchers.Main).launch {
                        mDatum?.memos?.removeAt(mSelectedIndex)
                        refreshRecyclerView()
                        mSelectedIndex = -1
                    }
                }
                Activity.RESULT_CANCELED -> {
                    mSelectedIndex = -1
                }
                else -> {

                }
            }
        }

    private fun editPhoto() {
        CommonUtil.showAlertDialog2(
            this@WriteActivity,
            mPhotoLauncher,
            "알림",
            "사진을 삭제하시겠습니까?",
            "취소",
            "확인"
        )
    }

    private val mImageLauncher = registerImagePicker {
        it.forEach { image ->
            mDatum?.memos?.add(MemoItemVO("B", image.uri.toString()))

            refreshRecyclerView()
        }
    }

    private fun openPhoto() {
        //  썸네일 변경 처리
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val granted: ArrayList<Boolean> = arrayListOf()
        if (!hasPermissions(permissions)) {
            requestPermissions(permissions, object : OnPermissionChecked {

                override fun onGranted(permission: String) {
                    granted.add(true)

                    //  모든 권한을 승인받았을 경우에만 처리
                    if (granted.size == permissions.size) {
                        var isGranted = true
                        for (i in 0 until granted.size) {
                            if (!granted[i]) {
                                isGranted = false
                            }
                        }
                        if (isGranted) {
                            openPhoto()
                        }
                    }
                }

                override fun onDenied(permission: String) {
                    granted.add(false)
                }
            })

            return
        }

        val config = ImagePickerConfig {
            mode = ImagePickerMode.SINGLE
            language = "ko"
            imageTitle = "이미지 선택"
            doneButtonText = "완료"
            limit = 1
            isShowCamera = true
        }
        mImageLauncher.launch(config)
    }

    /**
     * 권한 허용 여부
     * @param permissions : 권한
     * @return : [Boolean]
     */
    private fun hasPermissions(permissions: Array<out String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    /**
     * 권한 요청
     * @param permissions : 권한
     * @param listener : 완료 콜백
     */
    private fun requestPermissions(permissions: Array<out String>, listener: OnPermissionChecked) {
        this.mOnPermissionChecked = listener
        ActivityCompat.requestPermissions(this, permissions, 10001)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10001) {
            for (i in grantResults.indices) {
                if ((grantResults.isNotEmpty() && grantResults[i] == PackageManager.PERMISSION_GRANTED)) {
                    mOnPermissionChecked?.run {
                        onGranted(permissions[i])
                    }
                } else {
                    Snackbar.make(
                        binding.root,
                        "권한을 허용하지 않으셨습니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    mOnPermissionChecked?.run {
                        onDenied(permissions[i])
                    }
                }
            }
        }
        mOnPermissionChecked = null
    }

    private val mMapLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    //  데이터 삭제
                    CoroutineScope(Dispatchers.Main).launch {
                        mDatum?.memos?.removeAt(mSelectedIndex)
                        refreshRecyclerView()
                        mSelectedIndex = -1
                    }
                }
                Activity.RESULT_CANCELED -> {
                    mSelectedIndex = -1
                }
                else -> {

                }
            }
        }

    private fun editMap() {
        CommonUtil.showAlertDialog2(
            this@WriteActivity,
            mMapLauncher,
            "알림",
            "위치를 삭제하시겠습니까?",
            "취소",
            "확인"
        )
    }

    private fun findCurrentLocation() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
        val granted: ArrayList<Boolean> = arrayListOf()
        if (!hasPermissions(permissions)) {
            requestPermissions(permissions, object : OnPermissionChecked {

                override fun onGranted(permission: String) {
                    granted.add(true)

                    //  모든 권한을 승인받았을 경우에만 처리
                    if (granted.size == permissions.size) {
                        var isGranted = true
                        for (i in 0 until granted.size) {
                            if (!granted[i]) {
                                isGranted = false
                            }
                        }
                        if (isGranted) {
                            findCurrentLocation()
                        }
                    }
                }

                override fun onDenied(permission: String) {
                    granted.add(false)
                }
            })

            return
        }

        val locationManager: LocationManager? =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                locationManager?.removeUpdates(this)

                mDatum?.memos?.add(MemoItemVO("C", "${location.latitude},${location.longitude}"))
                refreshRecyclerView()
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

            override fun onProviderEnabled(provider: String) {

            }

            override fun onProviderDisabled(provider: String) {

            }
        }

        try {
            locationManager?.run {
                requestLocationUpdates(LocationManager.GPS_PROVIDER, 1L, 0f, locationListener)
                requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1L, 0f, locationListener)
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
            Toast.makeText(this@WriteActivity, "권한을 허용하지 않으셨습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}