package com.ihfazh.dicoding_mybroadcastreeceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

    final String TAG = SMSReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null){
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                if (pdusObj != null){
                    for (Object aPdusObj :
                            pdusObj) {
                        SmsMessage smsMessage = getIncomingMessage(aPdusObj, bundle);
                        String senderNum = smsMessage.getDisplayOriginatingAddress();
                        String message = smsMessage.getDisplayMessageBody();
                        Log.d(TAG, "From: " + senderNum + " message: " + message);

                        Intent smsIntent = new Intent(context, SmsRecieverActivity.class);
                        smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        smsIntent.putExtra(SmsRecieverActivity.NOMOR, senderNum);
                        smsIntent.putExtra(SmsRecieverActivity.MESSAGE, message);
                        context.startActivity(smsIntent);
                    }
                }
            } else {
                Log.d(TAG, "on receive: null");
            }
        } catch (Exception e){
            Log.d(TAG, "Exception onReceive: " + e);
        }
    }

    private SmsMessage getIncomingMessage(Object aObject, Bundle bundle){
        SmsMessage smsMessage;
        if (Build.VERSION.SDK_INT >= 23){
            String format = bundle.getString("format");
            smsMessage = SmsMessage.createFromPdu((byte[]) aObject, format);
        } else {
            smsMessage = SmsMessage.createFromPdu((byte[]) aObject);

        }
        return smsMessage;

    }
}
