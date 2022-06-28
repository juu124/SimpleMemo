package kr.co.witches.simplememo

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kr.co.witches.simplememo.data.database.MemoDatabase
import kr.co.witches.simplememo.data.database.repository.MemoRepository

class AppApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { MemoDatabase.getDatabase(this, applicationScope)}
    val repository by lazy { MemoRepository(database!!.memoDao()) }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

}