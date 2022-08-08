package kr.co.witches.simplememo.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.witches.simplememo.R

class SimpleMemoListAdapter(private val context: Context) : RecyclerView.Adapter<SimpleMemoListAdapter.ViewHolder>() {

    var datas = mutableListOf<RecyclerItem>()

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context: Context = parent.context
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.recyclerview_item, parent, false)
        val vh = ViewHolder(view)
        return vh
    }

    // 전체 아이템 갯수 리턴
    override fun getItemCount(): Int = datas!!.size

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtcontent: TextView = itemView.findViewById(R.id.tv_rv_content)
        fun bind(item: RecyclerItem) {
            txtcontent.text = item.toString()
            Glide.with(itemView).load(item.toString())
        }
    }
}

