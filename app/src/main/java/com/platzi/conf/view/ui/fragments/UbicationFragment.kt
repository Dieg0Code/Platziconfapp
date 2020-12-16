package com.platzi.conf.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.platzi.conf.R
import com.platzi.conf.model.Ubication
import kotlinx.android.synthetic.main.fragment_ubication.*

class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Load map on screen
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val ubication = Ubication()

        //zoom 1-19
        val zoom = 16f
        val centerMap = LatLng(ubication.latitud, ubication.latitud)

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        val centerMark = LatLng(ubication.latitud, ubication.longitud)
        val markerOptions = MarkerOptions()
        markerOptions.position(centerMark)
        markerOptions.title("PlatziConf")

        //Load markerPlatziIcon
        val bitMapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_platzi) } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitMapDraw.bitmap, 150, 150, false)

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        googleMap?.addMarker(markerOptions)
        googleMap?.setOnMarkerClickListener(this)

        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))

    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        findNavController().navigate(R.id.ubicationDetailDialogFragment)
        return  true
    }

}