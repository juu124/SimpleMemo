package kr.co.witches.simplememo.ui.main

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.location.LocationManagerCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kr.co.witches.simplememo.R
import kr.co.witches.simplememo.ui.write.WriteActivity
import java.lang.reflect.Array.newInstance


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.*/


class MapFragment : Fragment(),
    OnMapReadyCallback/*, OnMyLocationButtonClickListener, OnMyLocationClickListener*/ {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mContext: Context
    private lateinit var map: GoogleMap

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is WriteActivity) {
            mContext = context
        }
    }

    private lateinit var mView: MapView

    fun permissionCheck() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ){
            //위치 권한이 있으니 현재 위치 이동 시작(GPS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_map, container, false)
        mView = rootView.findViewById(R.id.mapView)
        mView.onCreate(savedInstanceState)
        mView.getMapAsync(this)

        var btnCurrentLocationRequest: FloatingActionButton
        btnCurrentLocationRequest = rootView.findViewById(R.id.btn_current_location)

        btnCurrentLocationRequest.setOnClickListener { view ->
            // 처음으로 권한 물어보기(허용안하면 권한 필요성 물어보기)
            val permissionCheckFineLocation =
                ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            val permissionCheckCoarseLocation =
                ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)

            if ((permissionCheckFineLocation == PackageManager.PERMISSION_DENIED)
                ||(permissionCheckCoarseLocation == PackageManager.PERMISSION_DENIED))
            {
                // 위치 권한이 없다면
                Toast.makeText(context, "내 위치 버튼 클릭됨", Toast.LENGTH_SHORT)
                    .show()
                ActivityCompat.requestPermissions(
                    requireContext() as Activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION),
                    0
                )
            } else {
                // 위치 권한이 있다면
                myCurrentLocation()
            }

            val permissionCheck2 = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
            if (permissionCheck2 == PackageManager.PERMISSION_DENIED) { //백그라운드 위치 권한 확인
                //위치 권한 요청
                ActivityCompat.requestPermissions(
                    requireContext() as Activity,
                    arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                    0
                )
            }
        }
        return rootView
    }

    private fun myCurrentLocation() {
        //GPS로 위치 이동하기
        val locationManager = context?.getSystemService(LOCATION_SERVICE) as LocationManager
        val criteria = Criteria().apply {
            accuracy = Criteria.ACCURACY_FINE
            isAltitudeRequired = false
            isBearingRequired = false
            isSpeedRequired = false
            isCostAllowed = true
            powerRequirement = Criteria.POWER_LOW
        }
        val locationProvider = locationManager.getBestProvider(criteria, true)

        val locationListener = object : LocationListener {
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                super.onStatusChanged(provider, status, extras)
                // provider의 상태가 변경될때마다 호출
                // deprecated
            }

            override fun onLocationChanged(location: Location) {
                // 위치 정보 전달 목적으로 호출(자동으로 호출)
                val longitude = location.longitude
                val latitude = location.latitude
                val altitude = location.altitude
                Log.d("Location", "Latitude : $latitude, Longitude : $longitude, altitude: $altitude")

            }

            override fun onProviderEnabled(provider: String) {
                super.onProviderEnabled(provider)
                // provider가 사용 가능한 생태가 되는 순간 호출
            }

            override fun onProviderDisabled(provider: String) {
                super.onProviderDisabled(provider)
                // provider가 사용 불가능 상황이 되는 순간 호출
            }
        }
        // 매개변수로 위치 정보 제공자, LocationListener 호출 주기, 변경 위치 거리의 정보, LocationListener
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10.0f, locationListener)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val marker = LatLng(35.16, 126.85)
        googleMap.addMarker(MarkerOptions().position(marker).title("여기"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))
    }

    override fun onResume() {
        super.onResume()
        mView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mView.onLowMemory()
    }

    override fun onDestroy() {
        mView.onDestroy()
        super.onDestroy()
    }
}
