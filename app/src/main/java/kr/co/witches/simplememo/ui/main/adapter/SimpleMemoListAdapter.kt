package kr.co.witches.simplememo.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.internal.maps.zzaa
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.witches.simplememo.R
import kr.co.witches.simplememo.model.MemoContentType
import kr.co.witches.simplememo.model.MemoContentVO
import kr.co.witches.simplememo.ui.main.RecyclerItem

class SimpleMemoListAdapter(
    private val context: Context,
    var data: ArrayList<MemoContentVO>,
    private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<SimpleMemoListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val mRootlayout: ConstraintLayout = itemView.findViewById(R.id.layout_memo_root)
        private val mMemoTextLayout: LinearLayout = itemView.findViewById(R.id.layout_memo_text)
        private val mMemoTextView: TextView = itemView.findViewById(R.id.tv_memo_text)
        private val mMapLayout: LinearLayout = itemView.findViewById(R.id.layout_memo_map)
        private val mMapContainerLayout: FrameLayout =
            itemView.findViewById(R.id.layout_map_container)
        private val mMapWrapperLayout: LinearLayout =
            itemView.findViewById(R.id.layout_memo_map_click)
        private val mMapView: MapView = itemView.findViewById(R.id.map_view)
        private val mAddressTextView: TextView = itemView.findViewById(R.id.tv_chat_my_map_address)
        private val mImageLayout: LinearLayout = itemView.findViewById(R.id.layout_memo_image)
        private val mImageView: ImageView = itemView.findViewById(R.id.iv_memo_image)

        fun bind(datum: MemoContentVO, onClickListener: View.OnClickListener, position: Int) {
            mRootlayout.setOnClickListener(onClickListener)
            mRootlayout.tag = position

            mMemoTextLayout.visibility = View.GONE
            mMapLayout.visibility = View.GONE
            mMapWrapperLayout.visibility = View.GONE
            mImageLayout.visibility = View.GONE

            if (datum.type == MemoContentType.A) {
                // 메모
                mMemoTextLayout.visibility = View.VISIBLE
                mMemoTextView.text = datum.contents
            } else if (datum.type == MemoContentType.B) {
                // 사진
                mImageLayout.visibility = View.VISIBLE
                Glide.with(context)
                    .load(datum.contents)
                    .placeholder(R.drawable.img_sample)
                    .into(mImageView)
            } else if (datum.type == MemoContentType.C) {
                // 지도
                mMapLayout.visibility = View.VISIBLE
                mMapWrapperLayout.visibility = View.VISIBLE
                mMapWrapperLayout.setOnClickListener(onClickListener)
                mMapWrapperLayout.tag = position

                if (mMapView != null) {
                    mMapView.onCreate(null)
                    mMapView.onResume()
                    mMapView.getMapAsync { googleMap ->
                        googleMap.uiSettings.isZoomControlsEnabled = false
                        val locations = data[position].contents.split(",")
                        if (locations.size == 2) {
                            val location = LatLng(locations[0].toDouble(), locations[1].toDouble())
                            CoroutineScope(Dispatchers.Main).launch {
                                googleMap.animateCamera(
                                    CameraUpdateFactory.newCameraPosition()
                                )
                            }
                        } else {
                            //  위치 정보 오류
                        }
                    }
                }
            }
        }
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    // 전체 아이템 갯수 리턴
    override fun getItemCount(): Int {
        return data.size
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], onClickListener, position)
    }
}

