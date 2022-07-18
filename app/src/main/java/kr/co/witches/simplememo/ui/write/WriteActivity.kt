package kr.co.witches.simplememo.ui.write

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kr.co.witches.simplememo.R
import kr.co.witches.simplememo.databinding.ActivityWriteBinding
import kr.co.witches.simplememo.model.MemoContentType
import kr.co.witches.simplememo.model.MemoContentVO
import kr.co.witches.simplememo.model.MemoVO


class WriteActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)

        //내용 입력
        val etContent = binding.etContent
        etContent.requestFocus()

        // todo :: 이미지
        binding.btnAddImg.setOnClickListener(View.OnClickListener {
            AlertDialog.Builder(this)
                .setTitle("이미지 업로드")
                .setMessage("업로드할 이미지 선택")
                .setPositiveButton("카메라", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        Toast.makeText(applicationContext, "카메라로 이동!!!", Toast.LENGTH_SHORT).show()
                        Log.d("test", "성공1")
                        //카메라 및 갤러리 접근을 위한 접근 확인
                        val cameraPermissionCheck = ContextCompat.checkSelfPermission(
                            this@WriteActivity,
                            Manifest.permission.CAMERA
                        )
                        Log.d("test", "성공2")
                        if (cameraPermissionCheck != PackageManager.PERMISSION_GRANTED) {
                            //권한이 없는 경우
                            Log.d("test", "권한 없음")
                            ActivityCompat.requestPermissions(
                                this@WriteActivity, arrayOf(Manifest.permission.CAMERA), 1000
                            )
                        } else {
                            //권한이 있는 경우
                            Log.d("test", "권한 있음")
                            //여기 아래부터 잘 안됨..
                            val REQUEST_IMAGE_CAPTURE = 1
                            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                                takePictureIntent.resolveActivity(packageManager)?.also {
                                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                                    Log.d("test", "카메라")
                                }
                            }
                        }
                        fun onRequestPermissionsResult(
                            requestCode: Int,
                            permissions: Array<out String>,
                            grantResults: IntArray
                        ) {
                            onRequestPermissionsResult(requestCode, permissions, grantResults)
                            if (requestCode == 1000) {
                                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) { //거부
                                    Toast.makeText(this@WriteActivity, "거부ㅠ", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    }
                })

                .setNegativeButton("갤러리", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        Toast.makeText(applicationContext, "갤러리로 이동!!!", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNeutralButton("취소", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        Toast.makeText(applicationContext, "취소!!!", Toast.LENGTH_SHORT).show()
                    }
                })
                .create()
                .show()

        })

        // 지도
        val mapFragment1: SupportMapFragment =
            supportFragmentManager.findFragmentById(R.id.f_map) as SupportMapFragment
        mapFragment1.getMapAsync(this)
        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.f_map, mapFragment)
            .commit()


        // 메모 추가 버튼
        binding.btnAddMemoCheck.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "메모추가버튼22", Toast.LENGTH_SHORT).show()
            if (etContent.text.isNotBlank()) {
                val content = ArrayList<MemoContentVO>()
                val text = MemoContentVO(MemoContentType.text, etContent.text.toString())
                // val image =
                // val location =
                content.add(text)
                val memo = MemoVO(null, content, System.currentTimeMillis(), 0, 0, 0)
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
        TODO("Not yet implemented")
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(37.56, 126.97))
                .title("Marker")
        )
    }
    /*var mMap = googleMap
    val SEOUL = LatLng(37.56, 126.97)

    val markerOptions = MarkerOptions()
    markerOptions.position(SEOUL)
    markerOptions.title("서울")
    markerOptions.snippet("한국의 수도")
    mMap.addMarker(markerOptions)

    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10F))*/
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
