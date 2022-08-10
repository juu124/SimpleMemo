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

    lateinit var bitmap: Bitmap

    var mDatum: MemoVO? = null

    // 카메라로 이동
    val cameraActivityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            // 값 담기
            val extras = it.data!!.extras
            // bitmap으로 타입 변경
            bitmap = extras?.get("data") as Bitmap
            // 화면에 사진 보여주기
            // binding.ivImage.setImageBitmap(bitmap)
            Log.d("TAG", "camera capture success")
            //Log.d("TAG", "take picture >>> ${binding.ivImage}")
        } else {
            Log.d("TAG", "camera capture false")
        }
    }

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

                // 사진
                binding.ibMemoPic.setOnClickListener {
                    Log.d("TAG", "ibMemoPic")
                    /*AlertDialog.Builder(this)
                        .setTitle("이미지 업로드")
                        .setMessage("업로드할 이미지 선택")
                        // 카메라
                        .setPositiveButton("카메라", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                Log.d("TAG", "ibMemoPic onclick")
                                // 카메라 접근을 위한 접근 확인 및 실행
                                if (checkPermissionCamera()) {
                                    // 권한 없는 경우
                                    requestPermissionCamera()
                                } else {
                                    // 권한 있는 경우
                                    takePictureIntent()

                                }
                            }
                        })
                        // 갤러리 이동
                        .setNegativeButton("갤러리", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                //  갤러리 접근을 위한 접근 확인
                                if (checkPermissionGallery()) {
                                    // 권한 있는 경우(이미지 가져오기)
                                    importImageIntent()
                                } else {
                                    // 권한 없는 경우(권한 재 요청)
                                    requestPermissionGallery()
                                }
                            }
                        })
                        .setNeutralButton("취소",
                            object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface, which: Int) {
                                }
                            })
                        .create()
                        .show()*/
                }

                // 영상
                binding.ibChatVideo.setOnClickListener {
                    Log.d("TAG", "ibChatVideo")
                }

                // 지도
                binding.ibMemoMap.setOnClickListener {
                    Log.d("TAG", "ibMemoMap")
                }
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

    /* 사진 - 카메라, 저장소(2개) 권한 부여
    * 지도 - 위치(2개) 권한 부여 */

    private val mOnClickListener = View.OnClickListener {
        val position = it.tag.toString().toInt()
        println(">>> position : $position")
        Log.d("TAG", "mOnClickListener")
    }

    // 카메라 권한
    private fun checkPermissionCamera(): Boolean {
        Log.d(
            "TAG",
            "checkPermissionCameraCapture"
        )
        val permissionCheckCamera =
            ContextCompat.checkSelfPermission(this@WriteActivity, Manifest.permission.CAMERA)
        val permissionCheckReadStorage =
            ContextCompat.checkSelfPermission(
                this@WriteActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        val permissionCheckWriteStorage =
            ContextCompat.checkSelfPermission(
                this@WriteActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        if ((permissionCheckCamera != PackageManager.PERMISSION_GRANTED)
            && (permissionCheckReadStorage != PackageManager.PERMISSION_GRANTED)
            && (permissionCheckWriteStorage != PackageManager.PERMISSION_GRANTED)
        ) {
            // 권한 부여 실패
            Log.d(
                "TAG",
                "permission fail camera , readStorage, writeStorage >>> " + permissionCheckCamera + permissionCheckReadStorage + permissionCheckWriteStorage
            )
            return true
        } else {
            // 권한 부여 성공
            Log.d(
                "TAG",
                "permission success camera , readStorage, writeStorage >>> " + permissionCheckCamera + permissionCheckReadStorage + permissionCheckWriteStorage
            )
            return false
        }
    }

    // 카메라 권한 없을 때
    private fun requestPermissionCamera() {
        Log.d("TAG", "requestPermissionCamera")
        ActivityCompat.requestPermissions(
            this@WriteActivity, arrayOf(
                android.Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), 1000
        )
    }

    // 카메라 권한 있는 경우(카메라 실행)
    /*private fun takePictureIntent() {
        Log.d("TAG", "takePictureIntent")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraActivityResult.launch(intent)
    }*/
}

