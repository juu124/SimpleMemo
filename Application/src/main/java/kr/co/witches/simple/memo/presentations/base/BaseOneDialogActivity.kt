package kr.co.witches.simple.memo.presentations.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kr.co.witches.simple.memo.R
import kr.co.witches.simple.memo.util.OnSingleClickListener

class BaseOneDialogActivity : AppCompatActivity() {

    companion object {
        var isRunning: Boolean = false
    }

    private lateinit var mTitleTextView: TextView
    private lateinit var mMessageTextView: TextView
    private lateinit var mConfirmButton: Button

    private var mTitleString: String? = "알림"
    private var mMessageString: String? = ""
    private var mButtonString: String? = "확인"

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_one_button)
        setFinishOnTouchOutside(true)

        try {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } catch (e: Exception) {

        }

        initViews()
    }

    override fun onStart() {
        super.onStart()
        isRunning = true
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        finish()
        overridePendingTransition(0, 0)
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    private fun initViews() {
        intent.getStringExtra("title")?.let {
            mTitleString = it
        }

        intent.getStringExtra("message")?.let {
            mMessageString = it
        }

        intent.getStringExtra("button")?.let {
            mButtonString = it
        }

        mTitleTextView = findViewById(R.id.dialog_title)
        mTitleTextView.text = mTitleString
        mMessageTextView = findViewById(R.id.dialog_message)
        mMessageTextView.text = mMessageString

        mConfirmButton = findViewById(R.id.btn_dialog_confirm)
        mConfirmButton.text = mButtonString
        mConfirmButton.setOnClickListener(object : OnSingleClickListener() {

            override fun onSingleClick(v: View?) {
                setResult(RESULT_OK)
                finish()
                overridePendingTransition(0, 0)
            }

        })

    }

    override fun onStop() {
        super.onStop()
        isRunning = false
    }

}