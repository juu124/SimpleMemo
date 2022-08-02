package kr.co.witches.simplememo.ui.write

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
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

class WriteActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityWriteBinding
    lateinit var memoViewModel: MemoViewModel

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
    private fun checkPermissionCamera(): Boolean {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
    }

    // 카메라 실행
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                //startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                val intent: Intent = Intent()
                intent.setType("image/*")
                intent.setAction(Intent.ACTION_GET_CONTENT)
                launcher.launch(intent)
                Log.d("test", "카메라")
            }
        }
    }

    @Override
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                //거부
                Toast.makeText(this@WriteActivity, "거부", Toast.LENGTH_SHORT)
                    .show()
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
        etContent.requestFocus()

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
                        if (checkPermissionCamera()) {
                            // 권한 있는 경우
                            dispatchTakePictureIntent()
                        } else {
                            // 권한 없는 경우
                            requestPermission()
                        }
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
                Toast.makeText(this, "데이터를 입력해주세요.", Toast.LENGTH_SHORT).show()
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
