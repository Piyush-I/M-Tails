package com.example.android.internetspeedtest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class BaseInfo extends AppCompatActivity {
    String infoString, strphoneType;
    TextView varText;
    static final int PERMISSION_READ_STATE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_info);
        Check();

    }

    private void Check() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            myTelephonyManager();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSION_READ_STATE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case PERMISSION_READ_STATE: {
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    myTelephonyManager();
                } else {
                    Toast.makeText(this,
                            "please providde necessary permissions", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void myTelephonyManager() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int phoneType = telephonyManager.getPhoneType();
        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strphoneType = "CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                strphoneType = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strphoneType = "NONE";
                break;
        }



        GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();

        List<SubscriptionInfo> subscriptionInfos = SubscriptionManager.from(getApplicationContext()).getActiveSubscriptionInfoList();
       SubscriptionInfo lsuSubscriptionInfo = subscriptionInfos.get(0);
        SubscriptionInfo lsuSubscriptionInfo1 = subscriptionInfos.get(1);



        String networkOperator = telephonyManager.getNetworkOperator();
        String mcc = networkOperator.substring(0, 3);
        String mnc = networkOperator.substring(3);
        int cid = cellLocation.getCid();
        int lac = cellLocation.getLac();


        varText = (TextView) findViewById(R.id.infoPage);
        infoString = "BASE STATION DETAILS: \n";
        infoString+="\nMCC:"+mcc;
        infoString+="\nMNC:"+mnc;
        infoString+="\nCID:"+Integer.toString(cid);
        infoString+="\nLAC:"+Integer.toString(lac);

        varText.setText(infoString);

    }




}
