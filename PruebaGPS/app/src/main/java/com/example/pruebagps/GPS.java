package com.example.pruebagps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class GPS extends AppCompatActivity implements LocationListener {

    Button button_location;
    TextView txt_Latitud, txt_Longitud, txt_pais,txt_locacion,txt_ubicacion;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_s);

        txt_Latitud = findViewById(R.id.txt1);
        txt_Longitud = findViewById(R.id.txt2);
        txt_pais = findViewById(R.id.txt3);
        txt_locacion = findViewById(R.id.txt4);
        txt_ubicacion = findViewById(R.id.txt5);
        button_location = findViewById(R.id.btn_ubicacion);
        //Runtime permissions
        if (ContextCompat.checkSelfPermission(GPS.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(GPS.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }


        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create method
                getLocation();
            }
        });



    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,GPS.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(GPS.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);
            String pais= addresses.get(0).getCountryName();
            String locacion= addresses.get(0).getLocality();
            double LONGITUD= addresses.get(0).getLatitude();
            double LATITUD= addresses.get(0).getLongitude();

            txt_ubicacion.setText(Html.fromHtml("<font color= '#6200EE'><b>Ubicacion :</b><br></font>" + address));
            txt_pais.setText(Html.fromHtml("<font color= '#6200EE'><b>Pais :</b><br></font>" + pais));
            txt_locacion.setText(Html.fromHtml("<font color= '#6200EE'><b>Locacion :</b><br></font>" + locacion));
            txt_Longitud.setText(Html.fromHtml("<font color= '#6200EE'><b>Longitud :</b><br></font>" + LONGITUD));
            txt_Latitud.setText(Html.fromHtml("<font color= '#6200EE'><b>Latitud :</b><br></font>" + LATITUD));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void Salir(View v){
        this.finish();
        System.exit(0);
    }
}
