package kr.co.witches.simplememo.ui.write

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kr.co.witches.simplememo.R
import kr.co.witches.simplememo.data.database.viewmodel.MemoViewModel
import kr.co.witches.simplememo.databinding.ActivityWriteBinding
import kr.co.witches.simplememo.model.MemoContentType
import kr.co.witches.simplememo.model.MemoContentVO
import kr.co.witches.simplememo.model.MemoVO
import kr.co.witches.simplememo.ui.main.MapFragment

class WriteActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityWriteBinding
    lateinit var memoViewModel: MemoViewModel
    lateinit var bitmap: Bitmap

    // 갤러리 이동 launcher 선언
    val galleryActivityResult = registerForActivityResult(
        StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            // 값 담기
            val uri: Uri? = intent!!.data
            // 화면에 사진 보여주기
            binding.ivImage.setImageURI(uri)
            Glide.with(this@WriteActivity)
                .load(uri)
                .into(binding.ivImage)
            Log.d("TAG", "import image success")
            //Log.d("TAG", "image >>> ${binding.ivImage.drawable}")
            Log.d("TAG", "image >>> ${ResourcesCompat.getDrawable(resources, R.drawable.img_sample, null)}")
            //Log.d("TAG", "iportbtn >>> ${binding.ivImage.setImageBitmap(bitmap)}")

        } else {
            Log.d("TAG", "import image false")
        }
    }

    // 카메라로 이동
    val cameraActivityResult = registerForActivityResult(
        StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            // 값 담기
            val extras = it.data!!.extras
            // bitmap으로 타입 변경
            bitmap = extras?.get("data") as Bitmap
            // 화면에 사진 보여주기
            binding.ivImage.setImageBitmap(bitmap)
            Log.d("TAG", "camera capture success")
            //Log.d("TAG", "take picture >>> ${binding.ivImage}")
        } else {
            Log.d("TAG", "camera capture false")
        }
    }

    // 카메라 권한 확인 T/F
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
        *//*if ((permissionCheckCamera != PackageManager.PERMISSION_GRANTED)
            && (permissionCheckReadStorage != PackageManager.PERMISSION_GRANTED)
            && (permissionCheckWriteStorage != PackageManager.PERMISSION_GRANTED)
        ) {
            // 권한 없음
            Log.d(
                "TAG",
                "camera , readStorage, writeStorage >>> " + permissionCheckCamera + permissionCheckReadStorage + permissionCheckWriteStorage
            )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                1000
            )
        } else {
            // 권한 허용
            Log.d(
                "TAG",
                "success camera , readStorage, writeStorage >>> " + permissionCheckCamera + permissionCheckReadStorage + permissionCheckWriteStorage
            )
            // 카메라 실행
            Log.d("TAG", "imagecCapture?")
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraActivityResult.launch(intent)
        }*//*
    }

    // 갤러리 권한 확인 T/F
    private fun checkPermissionGallery(): Boolean {
        val writePermission = ContextCompat.checkSelfPermission(
            this@WriteActivity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val readPermission = ContextCompat.checkSelfPermission(
            this@WriteActivity,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        return (writePermission != PackageManager.PERMISSION_DENIED || readPermission != PackageManager.PERMISSION_DENIED)
    }

    // 카메라 권한 없는 경우
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
    private fun takePictureIntent() {
        Log.d("TAG", "takePictureIntent")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraActivityResult.launch(intent)
    }

    // 갤러리 권한 없는 경우
    private fun requestPermissionGallery() {
        Log.d("TAG", "requestPermissionGallery")
        ActivityCompat.requestPermissions(
            this@WriteActivity, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ), 1000
        )
    }

    // 갤러리 권한 있는 경우
    private fun importImageIntent() {
        Log.d("TAG", "importImageIntent")
        val intent: Intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        galleryActivityResult.launch(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@WriteActivity, R.layout.activity_write)

        // 내용 입력
        val etContent = binding.etContent
        //etContent.requestFocus()

        // 이미지
        binding.btnAddImg.setOnClickListener(View.OnClickListener {
            AlertDialog.Builder(this)
                .setTitle("이미지 업로드")
                .setMessage("업로드할 이미지 선택")
                // 카메라
                .setPositiveButton("카메라", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        Toast.makeText(applicationContext, "카메라로 이동", Toast.LENGTH_SHORT).show()
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
                            Toast.makeText(applicationContext, "취소!!!", Toast.LENGTH_SHORT).show()
                        }
                    })
                .create()
                .show()
        })

        // 지도
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MapFragment()
        fragmentTransaction.add(R.id.f_map, fragment)
        fragmentTransaction.commit()

        // todo :: 메모 추가
        binding.btnAddMemoCheck.setOnClickListener(View.OnClickListener {
            if (etContent.text.isNotBlank()) {
                val content = ArrayList<MemoContentVO>()
                val text = MemoContentVO(MemoContentType.text, etContent.text.toString())
                content.add(text)
                val memo = MemoVO(null, content, System.currentTimeMillis(), 0, 0, 0)
*//*
                memoViewModel = ViewModelProvider(this, MemoViewModelFactory(application)).get(MemoViewModel::class.java)
*//*
                Toast.makeText(this, "메모추가버튼2", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "메모를 입력해주세요.", Toast.LENGTH_SHORT).show()
                etContent.requestFocus()
            }
        })

        // 메모 작성 취소 버튼
        //binding.btnBack.setOnClickListener(View.OnClickListener {
            AlertDialog.Builder(this)
                .setTitle("작성 취소")
                .setMessage("메모가 저장이 되지 않았습니다. \n그래도 나가시겠습니까?")
                .setPositiveButton("네", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        finish()
                    }
                })
                .setNegativeButton("아니오", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                    }
                })
                .create()
                .show()
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("마커!")
        )
    }
}