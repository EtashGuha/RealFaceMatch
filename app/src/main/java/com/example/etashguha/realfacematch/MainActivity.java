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
import android.graphics.drawable.*;
import android.widget.TextView;

import java.io.File;

import id.zelory.compressor.Compressor;

public class MainActivity extends AppCompatActivity {

    Button btnpic, switcher;
    ImageView imgTakenPic, imgToMimic;
    private static final int CAM_REQUEST = 1313;
    Bitmap pictureTaken;
    boolean isPictureSmiling;
    public double[] userbitmatpdata;
    Bitmap bMap;
    double [] theta;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnpic = (Button) findViewById(R.id.button);
        imgTakenPic = (ImageView) findViewById(R.id.imageView);
        imgToMimic = findViewById(R.id.imageToMimic);
        btnpic.setOnClickListener(new btnTakePhotoClicker());
        imgToMimic.setImageResource(R.drawable.realsmiling);
        isPictureSmiling = true;
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.realsmiling);
        imgToMimic.setImageBitmap(bMap);
        switcher = (Button) findViewById(R.id.switcheroo);
        score = findViewById(R.id.textView3);

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPictureSmiling == true) {
                    imgToMimic.setImageResource(R.drawable.realfrowning);
                    isPictureSmiling = false;
                    bMap = BitmapFactory.decodeResource(getResources(), R.drawable.realfrowning);
                    imgToMimic.setImageBitmap(bMap);
                } else {
                    imgToMimic.setImageResource(R.drawable.realsmiling);
                    isPictureSmiling = true;
                    bMap = BitmapFactory.decodeResource(getResources(), R.drawable.realsmiling);
                    imgToMimic.setImageBitmap(bMap);
                }
            }
        });
        double[][] X = new double[20][40001];
        for (int i = 0; i < 20; i++) {
            X[i][0] = 1;
        }
        for (int i = 1; i < 40001; i++) {
            X[0][i] = drawableJpgToBitmap(R.drawable.frown1, R.drawable.frown2)[i-1];
            System.out.println("a");
        }
        for (int i = 1; i < 40001; i++) {
            X[1][i] = drawableJpgToBitmap(R.drawable.frown3, R.drawable.frown4)[i-1];
            System.out.println("b");
        }
        for (int i = 1; i < 40001; i++) {
            X[2][i] = drawableJpgToBitmap(R.drawable.frown5, R.drawable.frown6)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[3][i] = drawableJpgToBitmap(R.drawable.frown7, R.drawable.frown8)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[4][i] = drawableJpgToBitmap(R.drawable.smile9, R.drawable.smile10)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[5][i] = drawableJpgToBitmap(R.drawable.smile1, R.drawable.frown1)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[6][i] = drawableJpgToBitmap(R.drawable.smile2, R.drawable.frown2)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[7][i] = drawableJpgToBitmap(R.drawable.smile3, R.drawable.frown3)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[8][i] = drawableJpgToBitmap(R.drawable.smile4, R.drawable.frown4)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[9][i] = drawableJpgToBitmap(R.drawable.frown9, R.drawable.frown10)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[10][i] = drawableJpgToBitmap(R.drawable.smile5, R.drawable.frown5)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[11][i] = drawableJpgToBitmap(R.drawable.smile6, R.drawable.frown6)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[12][i] = drawableJpgToBitmap(R.drawable.smile7, R.drawable.frown7)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[13][i] = drawableJpgToBitmap(R.drawable.smile8, R.drawable.frown8)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[14][i] = drawableJpgToBitmap(R.drawable.smile9, R.drawable.frown9)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[15][i] = drawableJpgToBitmap(R.drawable.smile10, R.drawable.frown10)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[16][i] = drawableJpgToBitmap(R.drawable.frown15, R.drawable.frown15)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[17][i] = drawableJpgToBitmap(R.drawable.frown16, R.drawable.frown16)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[18][i] = drawableJpgToBitmap(R.drawable.frown17, R.drawable.frown17)[i-1];
        }
        for (int i = 1; i < 40001; i++) {
            X[19][i] = drawableJpgToBitmap(R.drawable.frown18, R.drawable.frown18)[i-1];
        }
        double [] y = {75, 75, 75, 75, 75, 25, 25, 25, 25, 75, 25, 25, 25, 25, 25, 25, 75, 75, 75, 75, 75};
        GradientDescent grad = new GradientDescent(y, X, 1, 100);
         theta = grad.train();
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == CAM_REQUEST) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                pictureTaken = bitmap;
            }
            pictureTaken.createScaledBitmap(pictureTaken, 200, 200, false);
            double [] lastbMap = convertTo1DArray(pictureTaken, pictureTaken.getWidth(), pictureTaken.getHeight());
            double [] bMapdouble = convertTo1DArray(bMap, bMap.getWidth(), bMap.getHeight());
            double[] bMapdiff = difference(bMapdouble, lastbMap);
            double prediction = 0;
            for(int i = 1; i < theta.length; i++){
                prediction+= theta[i] * bMapdiff[i-1];
            }
            prediction += theta[0];
            prediction *= 100;
            score.setText("Score: " + prediction);


        }
        //convert jpg to bitmap
        public double[] drawableJpgToBitmap ( int x, int y){
            Bitmap bMap1 = BitmapFactory.decodeResource(getResources(), x);
            Bitmap bMap2 = BitmapFactory.decodeResource(getResources(), y);
            bMap1.createScaledBitmap(bMap1, 200, 200, false);
            bMap2.createScaledBitmap(bMap2, 200, 200, false);
            double[] bMap1double = convertTo1DArray(bMap1, bMap1.getWidth(), bMap1.getHeight());
            double[] bMap2double = convertTo1DArray(bMap2, bMap2.getWidth(), bMap2.getHeight());
            double[] bMapdiff = difference(bMap1double, bMap2double);
            return bMapdiff;
        }


        //1d array differences

        public double[] difference ( double[] bitMap1, double[] bitMap2){
            double[] differenceArray = new double[bitMap1.length];
            for (int i = 0; i < bitMap1.length; i++) {
                differenceArray[i] = Math.abs(bitMap1[i] - bitMap2[i]);

            }
            return differenceArray;

        }

        public void toGrayscale (Bitmap bmpOriginal)
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

        public double[] convertTo1DArray (Bitmap bmpGrayscale,int width, int height){
            int[] pixels = new int[width * height];
            double[] grayScale = new double[width * height];
            double grayScale1 = 0.0;
            bmpGrayscale.getPixels(pixels, 0, width, 0, 0, width, height);
            for (int i = 0; i < pixels.length; i++) {
                int r = Color.red(pixels[i]);
                int g = Color.green(pixels[i]);
                int b = Color.blue(pixels[i]);
                grayScale[i] = (r + g + b) / 3.0;
                grayScale1 += grayScale[i];

            }
            return grayScale;

        }

        public void convertTo2DArray (Bitmap bmpGrayscale,int width, int height){
            double[][] bmpGrayscaleValues = new double[height][width];
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++) {
                    int argb = bmpGrayscale.getPixel(j, i);
                /*
                String argbString = Integer.toString(argb);
                String r = argbString.substring(4,7);
                String g = argbString.substring(7,10);
                String b = argbString.substring(10,13);
                    */
                    int rInt = Color.red(argb);
                    int gInt = Color.green(argb);
                    int bInt = Color.blue(argb);
                    double grayScaleValue = (rInt + gInt + bInt) / 3.0;

                    bmpGrayscaleValues[i][j] = grayScaleValue;

                    //System.out.println(bmpGrayscaleValues[i][j]);

                }

        }


        class btnTakePhotoClicker implements Button.OnClickListener {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAM_REQUEST);
            }
        }

    }



