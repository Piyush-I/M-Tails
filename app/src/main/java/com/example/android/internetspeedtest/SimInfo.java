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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SimInfo extends AppCompatActivity {
    TextView varText;
    String info;
    String strphoneType = "";
    static final int PERMISSION_READ_STATE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_info);
        Check();
    }
    private void Check() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            myTelephonyManager();
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_PHONE_STATE},
                    PERMISSION_READ_STATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){

            case PERMISSION_READ_STATE:{
                if(grantResults.length >= 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    myTelephonyManager();
                }else{
                    Toast.makeText(this,
                            "please providde necessary permissions",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void myTelephonyManager() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int phoneType = manager.getPhoneType();
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

        List<SubscriptionInfo> subscriptionInfos = SubscriptionManager.from(getApplicationContext()).getActiveSubscriptionInfoList();
        SubscriptionInfo lsuSubscriptionInfo = subscriptionInfos.get(0);
        SubscriptionInfo lsuSubscriptionInfo1 = subscriptionInfos.get(1);
        String name = (String) lsuSubscriptionInfo.getCarrierName();
        String name1 = (String) lsuSubscriptionInfo1.getCarrierName();
        String IMEI_1 = manager.getImei(0);
        String IMEI_2 = manager.getImei(1);
        String CountryISO = manager.getNetworkCountryIso();
        boolean isRoaming = manager.isNetworkRoaming();
        int index1 = lsuSubscriptionInfo.getSimSlotIndex();
        int index2 = lsuSubscriptionInfo1.getSimSlotIndex();

         index1++;
         index2++;
        info = "Phone Details: \n";
        info+="\nSim 1:"+name;
        info+="\nType:"+strphoneType;
        info+="\nIMEI 1:"+IMEI_1;
        info+="\n Roaming:"+isRoaming;
        info+="\n Sim Slot:"+index1;
        info+="\n Country ISO:"+CountryISO;
        info+="\nSim 2:"+name1;
        info+="\nType:"+strphoneType;
        info+="\nIMEI 2:"+IMEI_2;
        info+="\n Roaming:"+isRoaming;
        info+="\n Sim Slot:"+index2;
        info+="\n Country ISO:"+CountryISO;




        varText = (TextView) findViewById(R.id.siminfo);

        varText.setText(info);

    }
}
