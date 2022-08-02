package kr.co.witches.simplememo.ui.main

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import kr.co.witches.simplememo.R
import kr.co.witches.simplememo.databinding.ActivityMainBinding
import kr.co.witches.simplememo.model.MemoContentType
import kr.co.witches.simplememo.model.MemoContentVO
import kr.co.witches.simplememo.model.MemoVO

class AddMemoActivity{} /*: AppCompatActivity() {

//    private lateinit var binding: Activ

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_memo)
        //val inputMathodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        //내용 입력
        val etContent: EditText = findViewById(R.id.et_content)
        //window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        etContent.requestFocus()

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
            if (etContent.text.isNotBlank()) {
                val content = ArrayList<MemoContentVO>()
                val text = MemoContentVO(MemoContentType.text, etContent.text.toString())
                // val image =
                // val location =
                content.add(text)
                val memo = MemoVO(null, content, System.currentTimeMillis(), 0, 0, 0)
            }
        }

        //메모 작성 취소 버튼
        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
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
        }

    }
}*/