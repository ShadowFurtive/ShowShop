package com.example.kocha.showshop;

import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.security.PublicKey;

public class SubBotton {

    private  MapActivity Mapa;
    private String imagen, calle,cPomes,cMaduixes,cZumitos, titol;
    private Double v,v1;
    private GoogleMap mapG;
    private Marker m;

    public SubBotton ( MapActivity map, String imagen,Double v, Double v1, GoogleMap mapG,String calle,String pomes,String cMaduixes,String Zumitos, String titol){
        this.Mapa=map;
        this.imagen=imagen;
        this.v=v;
        this.v1=v1;
        this.mapG=mapG;
        this.calle=calle;
        this.cMaduixes=cMaduixes;
        this.cPomes=pomes;
        this.cZumitos=Zumitos;
        this.titol=titol;
        this.Ready();
    }

    public void removeMarked(){
        this.m.remove();
    }

    public void Ready(){
        LatLng ametller = new LatLng(this.v, this.v1);

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(ametller)
                .title(this.titol)
                .snippet(this.calle)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        InfoWindowData info = new InfoWindowData();
        this.SetInfoFruta(info);
        info.setHotel(this.cPomes);
        info.setFood(this.cMaduixes);
        info.setTransport(this.cZumitos);



        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(Mapa);
        this.mapG.setInfoWindowAdapter(customInfoWindow);

        m = this.mapG.addMarker(markerOptions);
        m.setTag(info);
        m.showInfoWindow();


        this.mapG.moveCamera(CameraUpdateFactory.newLatLng(ametller));
    }

    public void SetInfoFruta(InfoWindowData info){
        switch(this.imagen){
            case "Pomes":
                info.setImage("snowqualmie");
                break;
            case "Peres":
                info.setImage("snowqualmie");
                break;
            case "Maduixes":
                info.setImage("snowqualmie");
                break;
        }
    }


}
