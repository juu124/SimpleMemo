package kr.co.witches.simplememo.ui.write

import android.Manifest
import android.content.ContentValues.TAG
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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kr.co.witches.simplememo.R
import kr.co.witches.simplememo.data.database.viewmodel.MemoViewModel
import kr.co.witches.simplememo.data.database.viewmodel.MemoViewModelFactory
import kr.co.witches.simplememo.databinding.ActivityWriteBinding
import kr.co.witches.simplememo.model.MemoContentType
import kr.co.witches.simplememo.model.MemoContentVO
import kr.co.witches.simplememo.model.MemoVO
import kr.co.witches.simplememo.ui.main.MapFragment
import java.io.File

class WriteActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityWriteBinding
    lateinit var memoViewModel: MemoViewModel
    val REQUEST_IMAGE_CAPTURE = 1
    // var permissionCheckCamera1 = -1

    // 갤러리 이동 launcher 선언
    val launcher = registerForActivityResult(
        StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            Log.d("test", "사진 선택 완료")
            val intent = result.data
            val uri: Uri? = intent!!.data
            binding.ivImage.setImageURI(uri);
            Glide.with(this@WriteActivity)
                .load(uri)
                .into(binding.ivImage)
        }
    }

    // 카메라 권한 요청
    private fun requestPermission() {
        val cameraPermissionCheck = ContextCompat.checkSelfPermission(
            this@WriteActivity,
            Manifest.permission.CAMERA
        )
        if (cameraPermissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.d("test", "카메라 권한 없음")
            ActivityCompat.requestPermissions(
                this@WriteActivity, arrayOf(Manifest.permission.CAMERA), 1000
            )
        }
    }

    // 카메라 권한 확인 T/F
    private fun checkPermissionCamera() {
        Log.d(
            "TAG",
            "checkPermissionCamera"
        )
        val permissionCheckCamera =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val permissionCheckReadStorage =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val permissionCheckWriteStorage =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (/*(permissionCheckCamera != PackageManager.PERMISSION_GRANTED)
            && */(permissionCheckReadStorage != PackageManager.PERMISSION_GRANTED)
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
            // 카메라 실행 하는 함수로 연결
            val REQUEST_IMAGE_CAPTURE = 1
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
        /*
        if (permissionCheckCamera1 == 0 && permissionCheckStorage1 == 0 && permissionCheckStorage2 == 0) {
            // 카메라 실행 하는 함수로 연결
            Log.d(
                "TAG",
                "camera , readStorage, writeStorage >>> " + permissionCheckCamera1 + permissionCheckStorage1 + permissionCheckStorage2
            )
            dispatchTakePictureIntent()
        }*/ /*else {
            // 거부된 권한 확인 , 요청
            Log.d(
                "TAG",
                "camera , readStorage, writeStorage >>> " + permissionCheckCamera1 + permissionCheckStorage1 + permissionCheckStorage2
            )
            return false
        }*/
        // Log.d("TAG", "checkPermissionCamera")
        // Log.d("TAG", "camera , readStorage, writeStorage >>> " + permissionCheckCamera1 + permissionCheckStorage1 + permissionCheckStorage2)
        //return (permissionCheckCamera1
        //        == PackageManager.PERMISSION_GRANTED)
        /*&& permissionCheckCamera2
        == PackageManager.PERMISSION_GRANTED)*/
    }

    // 카메라 실행
    private fun dispatchTakePictureIntent() {
        Log.d("TAG", "dispatchTakePictureIntent")
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }

        /*val REQUEST_IMAGE_CAPTURE = 1
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }*/
    }

    @Override
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            var count = grantResults.count { it == PackageManager.PERMISSION_DENIED }
            if (count != 0) {
                Toast.makeText(applicationContext, "권한을 동의해주세요.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
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

    // 갤러리 권한 없는 경우
    private fun requestPermissionGallery() {
        Log.d("test", "갤러리 권한 없음")
        ActivityCompat.requestPermissions(
            this@WriteActivity, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ), 1000
        )
    }

    // 갤러리 권한 있는 경우
    private fun TakePictureIntent() {
        Log.d("test", "갤러리 권한 있음")
        val intent: Intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        Log.d("test", "갤러리로 이동")
        launcher.launch(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@WriteActivity, R.layout.activity_write)

        // 내용 입력
        val etContent = binding.etContent
        //etContent.requestFocus()

        // todo :: 이미지
        binding.btnAddImg.setOnClickListener(View.OnClickListener {
            AlertDialog.Builder(this)
                .setTitle("이미지 업로드")
                .setMessage("업로드할 이미지 선택")
                // 카메라
                .setPositiveButton("카메라", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        Toast.makeText(applicationContext, "카메라로 이동", Toast.LENGTH_SHORT).show()
                        // 카메라 접근을 위한 접근 확인
                        // 코틀린 boolean은 true:0, false:-1
                        checkPermissionCamera()
                        /*if (checkPermissionCamera()) {
                            Log.d("TAG", "checkPermissionCamera result = 1")
                            // 권한 있는 경우
                            dispatchTakePictureIntent()
                        } else {
                            // 권한 없는 경우
                            Log.d("TAG", "checkPermissionCamera result = 0")
                            requestPermission()
                        }*/
                    }
                })
                // 갤러리 이동
                .setNegativeButton("갤러리", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        //  갤러리 접근을 위한 접근 확인
                        if (checkPermissionGallery()) {
                            // 권한 있는 경우
                            TakePictureIntent()
                        } else {
                            // 권한 없는 경우
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
/*
                memoViewModel = ViewModelProvider(this, MemoViewModelFactory(application)).get(MemoViewModel::class.java)
*/
                Toast.makeText(this, "메모추가버튼2", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "메모를 입력해주세요.", Toast.LENGTH_SHORT).show()
                etContent.requestFocus()
            }
        })

        // 메모 작성 취소 버튼
        binding.btnBack.setOnClickListener(View.OnClickListener {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 카메라 촬영을 하면 이미지뷰에 사진 삽입
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Bitmap으로 컨버전
            Log.d("TAG", "onActivityResult success!!!")
            val imageBitmap: Bitmap = data?.extras?.get("data") as Bitmap
            // 이미지뷰에 Bitmap으로 이미지를 입력
            binding.ivImage.setImageBitmap(imageBitmap)
        } else {
            Log.d("TAG", "onActivityResult fail!!!")
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("마커!")
        )
    }
}


//카메라 및 갤러리 접근을 위한 접근 확인
/*private fun checkPermission() {
    val cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
    if(cameraPermission == PackageManager.PERMISSION_GRANTED){
        requestPermission()
    }
}

private fun requestPermission() {
    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 99)
}*/
