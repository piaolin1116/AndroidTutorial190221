package com.example.a.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
/*
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
*/
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        SmsMessage[] msgs = new SmsMessage[pdus.length];
        for(int i=0; i<msgs.length; i++){
            msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String txt = msgs[i].getOriginatingAddress() + " / " + msgs[i].getMessageBody();
            Log.d("smsMessage",txt);
            Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
        }
    }
}
