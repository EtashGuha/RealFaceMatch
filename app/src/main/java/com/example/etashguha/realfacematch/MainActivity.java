package com.example.etashguha.realfacematch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import id.zelory.compressor.Compressor;

public class MainActivity extends AppCompatActivity {

    Button btnpic, switcher;
    ImageView imgTakenPic, imgToMimic;
    private static final int CAM_REQUEST=1313;
    Bitmap pictureTaken;
    boolean isPictureSmiling;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnpic = (Button) findViewById(R.id.button);
        imgTakenPic = (ImageView)findViewById(R.id.imageView);
        imgToMimic = findViewById(R.id.imageToMimic);
        btnpic.setOnClickListener(new btnTakePhotoClicker());
        imgToMimic.setImageResource(R.drawable.realsmiling);
        isPictureSmiling = true;
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.realsmiling);
        imgToMimic.setImageBitmap(bMap);
        switcher = (Button)findViewById(R.id.switcheroo);

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPictureSmiling == true){
                    imgToMimic.setImageResource(R.drawable.realfrowning);
                    isPictureSmiling = false;
                    Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.realfrowning);
                    imgToMimic.setImageBitmap(bMap);
                } else {
                    imgToMimic.setImageResource(R.drawable.realsmiling);
                    isPictureSmiling = true;
                    Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.realsmiling);
                    imgToMimic.setImageBitmap(bMap);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            pictureTaken = bitmap;
        }
    }

    class btnTakePhotoClicker implements  Button.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAM_REQUEST);
        }
    }

}

