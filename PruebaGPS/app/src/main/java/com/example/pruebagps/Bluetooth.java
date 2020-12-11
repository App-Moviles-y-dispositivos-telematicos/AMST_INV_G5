package com.example.pruebagps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Bluetooth extends AppCompatActivity {

    public static final int BLUETOOTH_RED_CODE= 1;
    public static final int REQUEST_DISCOVER_BT= 0;

    Button mOnBtn, mOffBtn, mDiscoverBtn;

    BluetoothAdapter mBlueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        mOnBtn        = findViewById(R.id.btn_on);
        mOffBtn       = findViewById(R.id.btn_off);
        mDiscoverBtn  = findViewById(R.id.btn_reconocido);

        //adapter
        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        //check if bluetooth is available or not
        if (mBlueAdapter == null){
            Toast.makeText(Bluetooth.this, "This device doesn't support Bluetooth",
                    Toast.LENGTH_LONG).show();
        }
        if (!mBlueAdapter.isEnabled()) {
            //Toast.makeText(Bluetooth.this, "Activar el Bluetooth", Toast.LENGTH_LONG).show();
        }else {
           // Toast.makeText(Bluetooth.this, "Es posible desactivar el Bluetooth",Toast.LENGTH_LONG).show();
        }

        //on btn click
        mOnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBlueAdapter.isEnabled()){
                    //intent to on bluetooth
                    Intent bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(bluetoothIntent, BLUETOOTH_RED_CODE);
                    Toast.makeText(Bluetooth.this, "Encendiendo Bluetooth...",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Bluetooth.this, "Bluetooth ya se encuentra encendido.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //off btn click
        mOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBlueAdapter.isEnabled()){
                    mBlueAdapter.disable();
                    Toast.makeText(Bluetooth.this, "Apagando Bluetooth...",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Bluetooth.this, "Bluetooth ya se encuentra apagado.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        mDiscoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBlueAdapter.isDiscovering()){
                    Toast.makeText(Bluetooth.this, "Dispositivo Reconocible.",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, REQUEST_DISCOVER_BT);
                }
            }
        });


    }

    public void Salir(View v){
        this.finish();
        System.exit(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(Bluetooth.this, "Bluetooth is ON", Toast.LENGTH_SHORT).show();

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(Bluetooth.this, "Bluetooth operation is cancelled",
                    Toast.LENGTH_SHORT).show();
        }

    }

}