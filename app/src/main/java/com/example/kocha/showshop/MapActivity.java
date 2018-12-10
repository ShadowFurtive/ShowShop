package com.example.kocha.showshop;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kocha.showshop.Modulos.PlaceInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener {

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "El mapa está listo", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: El mapa está listo ");
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(11);
        Button boton1 = (Button) findViewById(R.id.boton1);
        MarkerOptions markerOptions = null;
        boton1.setClickable(true);
        final MapActivity esto=this;
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                if(!pulsado){
                    MaestroPulsado();
                    pulsado=true;
                }else{
                    MaestroPulsadoOtra();
                    pulsado=false;
                }

            }
        });
        if (mLocationPermissionGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            init();
        }
    }
    private void MaestroPulsado(){
        Button BPomes = (Button) findViewById(R.id.BPomes);
        BPomes.setClickable(true);
        final MapActivity esto=this;
        BPomes.setVisibility(View.VISIBLE);
        BPomes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                removmark();
                Filas[0] = new SubBotton(esto, "Pomes", 41.220308, 1.5366098, mMap, "La Rambla, 6, 43700 El Vendrell, Tarragona", "Pomes de Barcelona", "1,52 €/KG", "--", "Superverd");
                Filas[1] = new SubBotton(esto, "Pomes", 41.2187713, 1.5362523, mMap, "Avinguda d'En Jaume Carner, 36, 43700 El Vendrell, Tarragona", "Pomes d'Origen", "1,32 €/KG", "--", "Casa Ametller");
                Filas[2] = new SubBotton(esto, "Pomes", 41.217863, 1.527331, mMap, "Av. del Tancat de la Plana, 43700 El Vendrell, Tarragona", "Pomes de la Comarca", "1,40 €/KG", "--", "Fruteria Marin");
                Filas[3] = new SubBotton(esto, "Pomes", 41.218864, 1.525794, mMap, "Carrer de les Flors, 74, 76, 43700 El Vendrell, Tarragona", "Pomes d'Origen", "1,32 €/KG", "--", "Casa Ametller");

            }
        });
        Button BMeduixes = (Button) findViewById(R.id.BMeduixes);
        BMeduixes.setVisibility(View.VISIBLE);
        BMeduixes.setClickable(true);
        BMeduixes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 // Code here executes on main thread after user presses button
                removmark();
                Filas[4] = new SubBotton(esto,"Pomes",41.218555,1.530169,mMap,"Via dels Països Catalans, 26, 43700 El Vendrell, Tarragona","Meduixes de Catalunya","1,08 €/KG","--","Condis");
                 Filas[5] = new SubBotton(esto,"Pomes",41.221195,1.523641,mMap,"Carrer Alt Empordà, 25-37, 43700 El Vendrell, Tarragona","Meduixes de Catalunya","1,08 €/KG","--","Condis Express");
                  Filas[6] = new SubBotton(esto,"Pomes",41.218762,1.536265,mMap,"Avinguda d'En Jaume Carner, 36, 43700 El Vendrell, Tarragona","Meduixes d'Origen","1,28 €/KG","--","Casa Ametller");

            }
        });

        Button BPeres = (Button) findViewById(R.id.BPeres);
        BPeres.setVisibility(View.VISIBLE);
        BPeres.setClickable(true);
        BPeres.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                removmark();
                Filas[7] = new SubBotton(esto,"Pomes",41.214347,1.529282,mMap,"PEPITOLOSPALOTES","Las mejores","Medias","medios","Pomes Origen");
                 Filas[8] = new SubBotton(esto,"Pomes",41.218574,1.530216,mMap,"PEPITOLOSPALOTES","Las mejores","Medias","medios","Pomes Origen");
                  Filas[9]= new SubBotton(esto,"Pomes",41.216368,1.546046,mMap,"PEPITOLOSPALOTES","Las mejores","Medias","medios","Pomes Origen");
            }
        });

    }
    private void removmark(){
        for (int i=0;i<Filas.length;i++){
            if(Filas[i]!=null) Filas[i].removeMarked();
        }
    }

    private void MaestroPulsadoOtra(){
        Button BPomes = (Button) findViewById(R.id.BPomes);
        BPomes.setClickable(false);
        BPomes.setVisibility(View.GONE);

        Button BMeduixes = (Button) findViewById(R.id.BMeduixes);
        BMeduixes.setVisibility(View.GONE);
        BMeduixes.setClickable(false);

        Button BPeres = (Button) findViewById(R.id.BPeres);
        BPeres.setVisibility(View.GONE);
        BPeres.setClickable(false);
        this.removmark();
    }





    private static final String TAG = "MapActivity";
    private boolean pulsado=false;
    private SubBotton []Filas= new SubBotton[9];

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71,136));

    //widgets
    private AutoCompleteTextView mSearchText;
    private ImageView mGps;

    //vars
    private Boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private PlaceAutocompleteAdapter mPlaceAutoCompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private PlaceInfo mPlace;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mSearchText = (AutoCompleteTextView) findViewById(R.id.input_search);
        mGps = (ImageView) findViewById(R.id.ic_gps);

        getLocationPermission();


    }

    private void init() {
        Log.d(TAG, "init: Inicializando");

        mGeoDataClient = Places.getGeoDataClient(this, null);
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this,this)
                .build();
        mSearchText.setOnItemClickListener(mAutocompleteClickListener);
        mPlaceAutoCompleteAdapter = new PlaceAutocompleteAdapter(this, mGeoDataClient, LAT_LNG_BOUNDS
                , null);
        mSearchText.setAdapter(mPlaceAutoCompleteAdapter);


        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {

                    //buscador
                    geoLocate();

                }
                return false;
            }
        });

        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Click sobre el icono gps");
                getDeviceLocation();
            }
        });

        hideSoftKeyboard();
    }



    private void geoLocate() {
        Log.d(TAG, "geoLocate: geolocalizando");

        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(MapActivity.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) {
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }
        if (list.size() > 0) {
            Address address = list.get(0);

            Log.d(TAG, "geoLocate: localización hallada " + address.toString());
            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
                    address.getAddressLine(0));
        }
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: Obteniendo la localización actual del dispositivo");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionGranted) {

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onCompete: Localización encontrada");
                            Location currentLocation = (Location) task.getResult();
                            if(currentLocation!=null)
                            moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),
                                    DEFAULT_ZOOM,
                                    "Mi localización");
                        } else {
                            Log.d(TAG, "onComplete: No se encuentra la localización");
                            Toast.makeText(MapActivity.this, "imposible encontrar la lcalización actual", Toast.LENGTH_SHORT);
                        }

                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: Excepción de seguridad: " + e.getMessage());
        }

    }

    private void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d(TAG, "moveCamera; Moviendo la camera al suelo" + latLng.latitude + ", lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if (!title.equals("My localización")) {
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }

        hideSoftKeyboard();
    }

    private void initMap() {
        Log.d(TAG, "initMap: iniciando mapa");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapActivity.this);
    }

// solicitud de permisos y reacción al rechazarlos
    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission; Recibiendo permisos");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: Solicitado.");
        mLocationPermissionGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            Log.d(TAG, "onRequestPermissionsResutl: Permiso denegado");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResutl: Permiso aceptado");
                    mLocationPermissionGranted = true;
                    //inicar el mp
                    initMap();
                }
            }
        }
    }
    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    /*
    <<<<<<<<<<<<<<<<<<<<<<<<<<<AUTOCOMPLETE FINILIZACIÓN>>>>>>>>>>>>>>>>>
     */

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            hideSoftKeyboard();

            final AutocompletePrediction item = mPlaceAutoCompleteAdapter.getItem(i);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if(!places.getStatus().isSuccess()){
                Log.d(TAG, "Sitio no encontrado" + places.getStatus().toString());
                places.release();
                return;
            }
            final Place place = places.get(0);

            try{
                mPlace = new PlaceInfo();
                mPlace.setName(place.getName().toString());
                mPlace.setAdress(place.getAddress().toString());
                mPlace.setId(place.getId());
                //mPlace.setAttributions(place.getAttributions().toString());
                mPlace.setLatLng(place.getLatLng());
                mPlace.setRating(place.getRating());
                mPlace.setPhoneNumber(place.getPhoneNumber().toString());
                mPlace.setWebsiteUri(place.getWebsiteUri());

                 Log.d(TAG, "sitio" + mPlace.toString());
            }catch (NullPointerException e){
                Log.e(TAG, "Nulo" + e.getMessage() );
            }

            moveCamera(new LatLng(place.getViewport().getCenter().latitude, place.getViewport().getCenter().longitude), DEFAULT_ZOOM, "Localización Encontrada");

            places.release();

        }
    };

}
