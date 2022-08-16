package kr.co.witches.simple.memo.presentations.write.adapter

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
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.overlay.Marker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.witches.simple.memo.R
import kr.co.witches.simple.memo.model.MemoItemVO

class WriteAdapter(
    private val context: Context,
    var data: ArrayList<MemoItemVO>,
    private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<WriteAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mRootLayout: ConstraintLayout = itemView.findViewById(R.id.layout_memo_root)
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

        fun bind(datum: MemoItemVO, onClickListener: View.OnClickListener, position: Int) {
            mRootLayout.setOnClickListener(onClickListener)
            mRootLayout.tag = position

            mMemoTextLayout.visibility = View.GONE
            mMapLayout.visibility = View.GONE
            mMapWrapperLayout.visibility = View.GONE
            mImageLayout.visibility = View.GONE

            if (datum.type == "A") {
                mMemoTextLayout.visibility = View.VISIBLE
                mMemoTextView.text = datum.contents
            } else if (datum.type == "B") {
                mImageLayout.visibility = View.VISIBLE
                Glide.with(context)
                    .load(datum.contents)
                    .placeholder(R.drawable.img_sample)
                    .into(mImageView)
            } else if (datum.type == "C") {
                mMapLayout.visibility = View.VISIBLE
                mMapWrapperLayout.visibility = View.VISIBLE
                mMapWrapperLayout.setOnClickListener(onClickListener)
                mMapWrapperLayout.tag = position

                if (mMapView != null) {
                    mMapView.onCreate(null)
                    mMapView.onResume()
                    mMapView.getMapAsync { naverMap ->
                        naverMap.uiSettings.isZoomControlEnabled = false
                        val locations = data[position].contents.split(",")
                        if (locations.size == 2) {
                            val location = LatLng(locations[0].toDouble(), locations[1].toDouble())
                            CoroutineScope(Dispatchers.Main).launch {
                                naverMap.moveCamera(
                                    CameraUpdate.toCameraPosition(
                                        CameraPosition(
                                            location,
                                            15.0
                                        )
                                    )
                                )

                                //  마커 추가
                                val marker = Marker()
                                marker.position = location
                                marker.map = naverMap
                            }
                        } else {
                            //  위치 정보 오류
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_write_memo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], onClickListener, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
    }
}