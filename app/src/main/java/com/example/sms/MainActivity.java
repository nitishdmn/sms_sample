package com.example.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt, phn_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (EditText)findViewById(R.id.txt);
        phn_num=(EditText)findViewById(R.id.phn_num);
    }
    public void send_msg(View view)
    {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(permissionCheck== PackageManager.PERMISSION_GRANTED)
        {

            Mymessage();
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},0);
        }
    }

    private void Mymessage() {
        String phone = phn_num.getText().toString().trim();
        String msg = txt.getText().toString().trim();

        if (!phn_num.getText().toString().equals("") || !txt.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, msg, null, null);
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Enter valid number and message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

     switch (requestCode){
         case 0:

             if(grantResults.length>=0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
             {

                 Mymessage();
             }
             else {
                 Toast.makeText(this, "You don't have Required Permission", Toast.LENGTH_SHORT).show();
             }


             break;
     }


    }
}
