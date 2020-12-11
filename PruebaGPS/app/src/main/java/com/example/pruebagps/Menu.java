package com.example.pruebagps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void irGps(View view) {
        Intent intent = new Intent(this, GPS.class);
        startActivity(intent);
    }

    public void irBluetooth(View view) {
        Intent intent = new Intent(this, Bluetooth.class);
        startActivity(intent);
    }

    public void salir(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}