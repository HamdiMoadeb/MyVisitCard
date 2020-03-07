package com.outsider.cartevisite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button btnemail, btnphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tx = findViewById(R.id.textv);
        btnemail = findViewById(R.id.emailbtn);
        btnphone = findViewById(R.id.phonebtn);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Pacifico-Regular.ttf");
        tx.setTypeface(custom_font);

        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+216 28572243"));

        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });











        btnemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "hamdimoadeb@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Hello Hamdi");
                email.putExtra(Intent.EXTRA_TEXT, "Cordialement.");
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }
        });



        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                    return;
                }else{
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
                }
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 1) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent);
            } else {

                Toast.makeText(MainActivity.this, "Permission denied to call phone", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
