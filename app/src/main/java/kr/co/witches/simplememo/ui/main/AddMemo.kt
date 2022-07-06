package kr.co.witches.simplememo.ui.main

import android.content.DialogInterface
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import kr.co.witches.simplememo.R

class AddMemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_memo)

        //제목 입력
        val editTitle: EditText = findViewById(R.id.editTitle)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        //내용 입력
        val editText: EditText = findViewById(R.id.editText)

        //이미지 추가(카메라, 로컬 이미지)
        val btnAddImg: Button = findViewById(R.id.btnAddImg)
        btnAddImg.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("이미지 업로드")
                .setMessage("업로드할 이미지 선택")
                .setPositiveButton("카메라", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        Toast.makeText(applicationContext, "카메라로 이동!!!", Toast.LENGTH_SHORT).show()
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
        }

        //지도

        //메모 추가 버튼
        val btnAddMemoCheck: Button = findViewById(R.id.btnAddMemoCheck)
        btnAddMemoCheck.setOnClickListener {
            Toast.makeText(this, "메모추가버튼22", Toast.LENGTH_SHORT).show()
        }

        //메모 작성 취소 버튼
        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

    }
}