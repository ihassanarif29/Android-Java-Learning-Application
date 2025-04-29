package com.example.android_java_learning_application;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BluetoothExampleActivity extends AppCompatActivity {



    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_CODE_BLUETOOTH_CONNECT = 1;
    private static final int REQUEST_PERMISSION_BLUETOOTH_CONNECT = 1;
    BluetoothReceiver bluetoothReceiver = new BluetoothReceiver();
    Button bluetoothOnOffButton;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bluetooth_example);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


//        if (!checkBluetoothPermission()) {
//            requestBluetoothPermission();
//        }

        bluetoothOnOffButton = findViewById(R.id.buttonBluetoothOnOff);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
            bluetoothOnOffButton.setEnabled(false);
        }

        bluetoothOnOffButton.setOnClickListener(new View.OnClickListener() {
            @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
            @Override
            public void onClick(View v) {
//                if (bluetoothAdapter.isEnabled()) {
//                    //bluetoothAdapter.disable();
//                    bluetoothOnOffButton.setText("Turn Bluetooth ON");
//                    //Toast.makeText(BluetoothExampleActivity.this, "Bluetooth Turned OFF", Toast.LENGTH_SHORT).show();
//                    turnOnBluetooth();
//                } else {
//                    //bluetoothAdapter.enable();
//                    bluetoothOnOffButton.setText("Turn Bluetooth OFF");
//                    //Toast.makeText(BluetoothExampleActivity.this, "Bluetooth Turned ON", Toast.LENGTH_SHORT).show();
//                    turnOffBluetooth();
//                }
            }
        });

        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(bluetoothReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(bluetoothReceiver);
    }

    private void turnOnBluetooth() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, REQUEST_PERMISSION_BLUETOOTH_CONNECT);
            } else {
                //Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                //startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        } else {
            Toast.makeText(this, "Bluetooth already ON", Toast.LENGTH_SHORT).show();
        }
    }

    private void turnOffBluetooth() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bluetoothAdapter.isEnabled()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, REQUEST_PERMISSION_BLUETOOTH_CONNECT);
            } else {
                bluetoothAdapter.disable();
                Toast.makeText(this, "Bluetooth turned OFF", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Bluetooth already OFF", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_BLUETOOTH_CONNECT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted. Try again.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }



//    private boolean checkBluetoothPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            return ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED;
//        } else {
//            return true; // No need to check permission below Android 12
//        }
//    }
//
//    private void requestBluetoothPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, REQUEST_CODE_BLUETOOTH_CONNECT);
//        }
//    }
//
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_BLUETOOTH_CONNECT) {
////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                toggleBluetooth();
////            } else {
////                Toast.makeText(this, "Bluetooth Permission Denied", Toast.LENGTH_SHORT).show();
////            }
//        }
//    }
}