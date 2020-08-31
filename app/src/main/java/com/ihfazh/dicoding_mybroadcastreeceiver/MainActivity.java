package com.ihfazh.dicoding_mybroadcastreeceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    public final static String DOWNLOAD_STATUS = "download status";

    private Button btnCheckSMSPerm, btnDownload;
    private BroadcastReceiver broadcastReceiver;

    final private int SMS_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheckSMSPerm = findViewById(R.id.check_sms_permission_btn);
        btnCheckSMSPerm.setOnClickListener(this);

        btnDownload = findViewById(R.id.download_button);
        btnDownload.setOnClickListener(this);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "Download selesai...");
                Toast.makeText(MainActivity.this, "Download Selesai", Toast.LENGTH_SHORT).show();
            }
        };

        IntentFilter intentFilter = new IntentFilter(DOWNLOAD_STATUS);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.check_sms_permission_btn:
                PermissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE);
                break;
            case R.id.download_button:
                Intent downloadIntent = new Intent(MainActivity.this, DownloadService.class);
                startService(downloadIntent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == SMS_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null){
            unregisterReceiver(broadcastReceiver);
        }
    }
}