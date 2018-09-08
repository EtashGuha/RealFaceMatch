package com.example.etashguha.realfacematch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
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

        pictureTaken.createScaledBitmap(pictureTaken,150,200,false);
        convertTo1DArray(pictureTaken, pictureTaken.getWidth(),pictureTaken.getHeight());

    }

    public void toGrayscale(Bitmap bmpOriginal)
    {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);

    }

    public double [] convertTo1DArray(Bitmap bmpGrayscale, int width, int height){
        int [] pixels = new int[width*height];
        double [] grayScale = new double[width*height];
        double grayScale1 = 0.0;
        bmpGrayscale.getPixels(pixels,0,width, 0,0, width, height);
        for(int i = 0; i < pixels.length; i++ ){
            int r = Color.red(pixels[i]);
            int g = Color.green(pixels[i]);
            int b = Color.blue(pixels[i]);
            grayScale[i] = (r+g+b)/3.0;
            grayScale1 += grayScale[i];

        }
        return grayScale;

    }

    public void convertTo2DArray(Bitmap bmpGrayscale, int width, int height){
        double [][] bmpGrayscaleValues = new double[height][width];
        for(int i = 0; i< height; i++)
            for(int j = 0; j < width; j++){
                int argb = bmpGrayscale.getPixel(j,i);
                /*
                String argbString = Integer.toString(argb);
                String r = argbString.substring(4,7);
                String g = argbString.substring(7,10);
                String b = argbString.substring(10,13);
                    */
                int rInt = Color.red(argb);
                int gInt = Color.green(argb);
                int bInt = Color.blue(argb);
                double grayScaleValue = (rInt+gInt+bInt)/3.0;

                bmpGrayscaleValues[i][j] = grayScaleValue;

                //System.out.println(bmpGrayscaleValues[i][j]);

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

