package kr.co.witches.simplememo.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kr.co.witches.simplememo.R

class SimpleMemoListAdapter : RecyclerView.Adapter<SimpleMemoListAdapter.ViewHolder>() {

    var mData: ArrayList<String>? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

        //lateinit var textTitle: TextView

        val textView: TextView

        init {
            textView = itemView.findViewById(R.id.tvTitle)
        }


//        fun ViewHolder(itemView: ActionMenuItemView){
//            super.itemView
//            textTitle = itemView.findViewById(R.id.tvTitle)
//        }
    }

    fun SimpleMemoListAdapter(list: ArrayList<String>){
        var mData = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context: Context = parent.context
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.recyclerview_item, parent, false)
        val vh: ViewHolder = ViewHolder(view)

        return vh
    }

    override fun onBindViewHolder(holder: SimpleMemoListAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
        val text: String = mData?.get(position).toString()
        holder.textView.setText(text.toInt())
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return mData!!.size
    }

}
