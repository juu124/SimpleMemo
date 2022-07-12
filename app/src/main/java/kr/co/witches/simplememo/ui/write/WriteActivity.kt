package kr.co.witches.simplememo.ui.write

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.witches.simplememo.R
import kr.co.witches.simplememo.databinding.ActivityMainBinding
import kr.co.witches.simplememo.databinding.ActivityWriteBinding
import kr.co.witches.simplememo.databinding.ActivityWriteBindingImpl
import kr.co.witches.simplememo.model.MemoContentType
import kr.co.witches.simplememo.model.MemoContentVO
import kr.co.witches.simplememo.model.MemoVO

class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)

        //내용 입력
        binding.etContent.requestFocus()

        // todo :: 이미지
        binding.btnAddImg.setOnClickListener(View.OnClickListener {
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
        })

        // todo :: 지도 추가하기
        // 메모 추가 버튼
        binding.btnAddMemoCheck.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "메모추가버튼22", Toast.LENGTH_SHORT).show()
            val etContent: TextView = findViewById(R.id.et_content)
            if (etContent.text.isNotBlank()){
                val content = ArrayList<MemoContentVO>()
                val text = MemoContentVO(MemoContentType.text, etContent.text.toString())
                // val image =
                // val location =
                content.add(text)
                val memo = MemoVO(null, content, System.currentTimeMillis(), 0, 0, 0)
            }
        })

        // 메모 작성 취소 버튼
        binding.btnBack.setOnClickListener(View.OnClickListener{
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
}