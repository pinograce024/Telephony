package com.example.telephony;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton callNum;
    TextView secT;
    EditText msgT;
    Button bSend;
    TextView sTtxt;
    EditText mTxt;
    Button btnS;
    TextView secText;
    EditText msgText;
    Button btnSEND;
    TextView Txt;
    EditText msTxt;
    Button bnSend;
    TextView secTxt;
    EditText msgTxt;
    Button btnSend;
    TextView number1;
    Button btn;
    EditText numTxt;
    String sNum;
    //String
    String sent = "SMS_SENT";
    String delivered = "SMS_DELIVERED";


    //CLASS

    PendingIntent sentPi;
    PendingIntent deliveredPi;

    BroadcastReceiver smsSentReceiver;
    BroadcastReceiver smsDeliveredReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        secT = findViewById(R.id.secT);
        msgT = findViewById(R.id.msgT);
        bSend = findViewById(R.id.bSend);
        sTtxt = findViewById(R.id.sTtxt);
        mTxt = findViewById(R.id.mTxt);
        btnS = findViewById(R.id.btnS);
        secText = findViewById(R.id.secText);
        msgText = findViewById(R.id.msgText);
        btnSEND = findViewById(R.id.btnSEND);
        Txt = findViewById(R.id.Txt);
        msTxt = findViewById(R.id.msTxt);
        bnSend = findViewById(R.id.bnSend);
        secTxt = findViewById(R.id.secTxt);
        msgTxt = findViewById(R.id.msgTxt);
        btnSend = findViewById(R.id.btnSend);
        btn = findViewById(R.id.idbtnCall);
        numTxt = findViewById(R.id.idNumtxt);

        //text view number

        number1 = findViewById(R.id.secT);

        //PendingIntent
        sentPi = PendingIntent.getBroadcast(this, 0, new Intent(sent), 0);
        deliveredPi = PendingIntent.getBroadcast(this, 0, new Intent(delivered), 0);
    }


    @Override
    public void onResume() {
        super.onResume();

        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        display("Message Sent");
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        display("Message Failed");
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        display("No Signal");
                        break;

                }


            }
        };// smsSentReceiver

        smsDeliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        display("OK");
                        break;
                    case Activity.RESULT_CANCELED:
                        display("Canceled");
                        break;
                }

            }
        };//smsDeliveredReceiver

        registerReceiver(smsSentReceiver, new IntentFilter(sent));
        registerReceiver(smsDeliveredReceiver, new IntentFilter(delivered));

    }//onResume

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(smsSentReceiver);
        unregisterReceiver(smsDeliveredReceiver);


    }//onPause


    //Button Call


    public void btnCall(View view) {
        Intent i = new Intent(Intent.ACTION_CALL);
        sNum = numTxt.getText().toString();
        if (sNum.trim().isEmpty()) {
            i.setData(Uri.parse("tel:1234567890"));
        } else {
            i.setData(Uri.parse("tel:" + sNum));
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Please grant the permession to call", Toast.LENGTH_SHORT).show();
            requestPermission();
        } else {
            startActivity(i);
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }




//Onclick Calling Number










    //Button SMS

    public void btnSend(View view) {
        int permissionCheck =
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS);
        if (permissionCheck ==
                PackageManager.PERMISSION_GRANTED) {
            sendMessage();
        } else {
            ActivityCompat.requestPermissions(this, new
                    String[]{Manifest.permission.SEND_SMS}, 0);
        }
    }

    private void sendMessage() {

        String phone, message;
        phone = secT.getText().toString().trim();
        message = msgT.getText().toString().trim();
        SmsManager smsManager =
                SmsManager.getDefault();

        smsManager.sendTextMessage(phone, null,
                message, null, null);
        Toast.makeText(this, "Message Sent",
                Toast.LENGTH_SHORT).show();
    }




    public void bnSend (View view) {
        int permissionCheck =
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS);
        if (permissionCheck==
                PackageManager.PERMISSION_GRANTED){
            setBnSend();
        }else {
            ActivityCompat.requestPermissions(this, new
                    String[]{Manifest.permission.SEND_SMS}, 0);
        }
    }

    private void setBnSend () {

        String phone, message;
        phone= Txt.getText().toString().trim();
        message = msTxt.getText().toString().trim();
        SmsManager smsManager=
                SmsManager.getDefault();

        smsManager.sendTextMessage(phone, null,
                message, null, null);
        Toast.makeText(this, "Message Sent",
                Toast.LENGTH_SHORT).show();
    }
    public void bSend (View view) {
        int permissionCheck =
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS);
        if (permissionCheck==
                PackageManager.PERMISSION_GRANTED){
            setBSend();
        }else {
            ActivityCompat.requestPermissions(this, new
                    String[]{Manifest.permission.SEND_SMS}, 0);
        }
    }

    private void setBSend () {

        String phone, message;
        phone= sTtxt.getText().toString().trim();
        message = mTxt.getText().toString().trim();
        SmsManager smsManager=
                SmsManager.getDefault();

        smsManager.sendTextMessage(phone, null,
                message, null, null);
        Toast.makeText(this, "Message Sent",
                Toast.LENGTH_SHORT).show();
    }
    public void btSend (View view) {
        int permissionCheck =
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS);
        if (permissionCheck==
                PackageManager.PERMISSION_GRANTED){
            BSend();
        }else {
            ActivityCompat.requestPermissions(this, new
                    String[]{Manifest.permission.SEND_SMS}, 0);
        }
    }

    private void BSend () {

        String phone, message;
        phone= secText.getText().toString().trim();
        message = msgText.getText().toString().trim();
        SmsManager smsManager=
                SmsManager.getDefault();

        smsManager.sendTextMessage(phone, null,
                message, null, null);
        Toast.makeText(this, "Message Sent",
                Toast.LENGTH_SHORT).show();
    }
    public void Send (View view) {
        int permissionCheck =
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS);
        if (permissionCheck==
                PackageManager.PERMISSION_GRANTED){
            Send();
        }else {
            ActivityCompat.requestPermissions(this, new
                    String[]{Manifest.permission.SEND_SMS}, 0);
        }
    }

    private void Send () {

        String phone, message;
        phone= secTxt.getText().toString().trim();
        message = msgTxt.getText().toString().trim();
        SmsManager smsManager=
                SmsManager.getDefault();

        smsManager.sendTextMessage(phone, null,
                message, null, null);
        Toast.makeText(this, "Message Sent",
                Toast.LENGTH_SHORT).show();
    }



    //SMS Method
    //tOAST
    public void display(String msg){
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
    }


}