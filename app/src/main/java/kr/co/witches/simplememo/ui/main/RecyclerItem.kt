package kr.co.witches.simplememo.ui.main

class RecyclerItem {
    private var content: String? = null

    fun setContent(title: String?) {
        content = title
    }

    fun getContent(): String? {
        return content
    }
}