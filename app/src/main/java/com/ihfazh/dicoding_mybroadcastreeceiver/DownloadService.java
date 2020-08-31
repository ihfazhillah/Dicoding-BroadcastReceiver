package com.ihfazh.dicoding_mybroadcastreeceiver;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class DownloadService extends IntentService {

    private final static String TAG = DownloadService.class.getSimpleName();

    public DownloadService() {
        super("DownloadService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Service dijalankan.....");
        if (intent != null) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent notifyFinishedIntent = new Intent(MainActivity.DOWNLOAD_STATUS);
            sendBroadcast(notifyFinishedIntent);
        }
    }

}
