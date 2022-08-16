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

class BaseTwoDialogActivity : AppCompatActivity() {

    companion object {
        var isRunning: Boolean = false
    }

    private lateinit var mTitleTextView: TextView
    private lateinit var mMessageTextView: TextView
    private lateinit var mLeftButton: Button
    private lateinit var mRightButton: Button

    private var mTitleString: String? = "알림"
    private var mMessageString: String? = ""
    private var mLeftString: String? = "취소"
    private var mRightString: String? = "확인"

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_two_button)
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

        intent.getStringExtra("left")?.let {
            mLeftString = it
        }

        intent.getStringExtra("right")?.let {
            mRightString = it
        }

        mTitleTextView = findViewById(R.id.dialog_title)
        mTitleTextView.text = mTitleString
        mMessageTextView = findViewById(R.id.dialog_message)
        mMessageTextView.text = mMessageString

        mLeftButton = findViewById(R.id.btn_dialog_left)
        mLeftButton.text = mLeftString
        mLeftButton.setOnClickListener(object : OnSingleClickListener() {

            override fun onSingleClick(v: View?) {
                setResult(RESULT_CANCELED)
                finish()
                overridePendingTransition(0, 0)
            }

        })

        mRightButton = findViewById(R.id.btn_dialog_right)
        mRightButton.text = mRightString
        mRightButton.setOnClickListener(object : OnSingleClickListener() {

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