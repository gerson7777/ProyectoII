package Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abyte.proyectoii.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Fragment06 extends Fragment implements OnMapReadyCallback {
    private  View rootView;
    private MapView mapView;
    private GoogleMap gMap;
    public Fragment06() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fragment06, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) rootView.findViewById(R.id.map);
        if (mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng Alzatate = new LatLng(14.495731439236692, -90.05676068845031);
        gMap.addMarker(new MarkerOptions().position(Alzatate).title("Finca San Carlos Alzatate").draggable(true));
        CameraPosition camara = new CameraPosition.Builder()
                .target(Alzatate)
                .zoom(18)
                .bearing(0)
                .tilt(30)
                .build();
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(camara));
        //gMap.moveCamera(CameraUpdateFactory.newLatLng(Alzatate));
    }
}
