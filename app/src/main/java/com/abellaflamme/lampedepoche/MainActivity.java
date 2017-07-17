package com.abellaflamme.lampedepoche;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CameraManager cameraManager;
    private String cameraId;
    private boolean flashIsOn = false;

    private Button bouton;
    private Drawable iconOff;
    private Drawable iconOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            cameraId = cameraManager.getCameraIdList()[0];

        }catch (NullPointerException e1){
            Log.e("lampedepoche", "No camera");
            e1.printStackTrace();
        }catch (Exception e2){
            Log.e("lampedepoche", "d'oh!");
            e2.printStackTrace();
            finish();
            return;
        }

        bouton = findViewById(R.id.bouton);

        iconOff = getDrawable(R.drawable.flash_off);
        iconOn = getDrawable(R.drawable.flash_on);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (flashIsOn) {
            turnOff();
        }
    }

    public void switchMe(View v){
        if (flashIsOn) {
            turnOff();
        }else {
            turnOnOhYeahBabyILikeThat();
        }
    }

    private void turnOnOhYeahBabyILikeThat(){
        try {
            flashIsOn = true;
            bouton.setBackground(iconOff);

            cameraManager.setTorchMode(cameraId, true);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void turnOff(){
        try {
            flashIsOn = false;
            bouton.setBackground(iconOn);

            cameraManager.setTorchMode(cameraId, false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
