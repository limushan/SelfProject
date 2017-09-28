/*
package com.limushan.mushanproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

public class ImageHanderActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private ImageView mImageView;
    private SeekBar mRotate;
    private SeekBar mSaturation;
    private SeekBar mScale;

    private Bitmap mBitmap;

    private ColorMatrix mRotateMatrix = new ColorMatrix();
    private ColorMatrix mSaturationMatrix = new ColorMatrix();
    private ColorMatrix mScaleMatrix = new ColorMatrix();
    private ColorMatrix mMatrix = new ColorMatrix();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       */
/* mImageView = (ImageView) findViewById(R.id.imageView);

        mRotate = (SeekBar) findViewById(R.id.rotate);
        mSaturation = (SeekBar) findViewById(R.id.saturation);
        mScale = (SeekBar) findViewById(R.id.scale);*//*


        mRotate.setOnSeekBarChangeListener(this);
        mSaturation.setOnSeekBarChangeListener(this);
        mScale.setOnSeekBarChangeListener(this);
        handleImage();
    }

    private void handleImage() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mei);
        mImageView.setImageBitmap(mBitmap);
    }


    @Override
    public void onClick(View v) {

    }

    private void setBitmap() {
        mImageView.setImageBitmap(getBitmap());
    }

    public Bitmap getBitmap() {
        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
       */
/* mMatrix.postConcat(mRotateMatrix);
        mMatrix.postConcat(mSaturationMatrix);
        mMatrix.postConcat(mScaleMatrix);*//*


        paint.setColorFilter(new ColorMatrixColorFilter(mMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);

        return bmp;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            //色调
            case R.id.rotate:
                mRotateMatrix.setRotate(0, progress);
                setBitmap();
                break;
            case R.id.saturation:
                mSaturationMatrix.setSaturation(progress);
                setBitmap();
                break;
            case R.id.scale:
                */
/*mMatrix.reset();
                mMatrix.setScale(progress, 0, 0, 1);
                setBitmap();*//*

              */
/*  mScaleMatrix.setScale(progress, 100, 20, 1);
                setBitmap();*//*

                */
/*float[] array = new float[]{0.33f, 0.59f, 0.11f, 0, 0, 0.33f, 0.59f, 0.11f, 0, 0, 0.33f, 0.59f, 0.11f, 0, 0, 0, 0, 0, 1, 0};
                mMatrix.set(array);
                setBitmap();*//*

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap pixelBitmap = oldRemeberImage(mBitmap);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mImageView.setImageBitmap(pixelBitmap);
                            }
                        });
                    }
                }).start();

                break;
        }
    }

    private Bitmap getPixelBitmap() {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        int length = width * height;
        int[] srcPixels = new int[length];
        int[] newPixels = new int[length];
        mBitmap.getPixels(srcPixels, 0, width, 0, 0, width, height);
        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        for (int i = 1; i < length; i++) {
            int prePixel = srcPixels[i - 1];
            int preR = Color.red(prePixel);
            int preG = Color.green(prePixel);
            int preB = Color.blue(prePixel);
            int alpha = Color.alpha(prePixel);

            int postPixel = srcPixels[i];
            int postR = Color.red(postPixel);
            int postG = Color.green(postPixel);
            int postB = Color.blue(postPixel);

            */
/**
             * 浮雕效果算法
             * 设f(i,j)像素为(r1, g1, b1) , f(i-1,j-1)像素为(r2,g2,b2), g(i,j)像素为(r,g,b)
             * g(i, j) = f(i, j) - f(i - 1, j)+ 常数 ， 这里的常数通常选作128
             *//*


            preR = postR - preR + 128;
            preG = postG - preG + 128;
            preB = postB - preB + 128;
            //均小于等于255 大于等于0
            preR = Math.min(255, Math.max(0, preR));
            preG = Math.min(255, Math.max(0, preG));
            preB = Math.min(255, Math.max(0, preB));

            newPixels[i] = Color.argb(alpha, preR, preG, preB);
        }

        bmp.setPixels(newPixels, 0, width, 0, 0, width, height);
        return bmp;
    }

    */
/**
     * 图片锐化（拉普拉斯变换）
     *
     * @param mBitmapSrc 图片源
     * @return Bitmap
     *//*

    public Bitmap sharpenImageAmeliorate(Bitmap mBitmapSrc) {
        // 拉普拉斯矩阵
        int[] laplacian = new int[]{-1, -1, -1, -1, 9, -1, -1, -1, -1};
        int width = mBitmapSrc.getWidth();
        int height = mBitmapSrc.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);
        int pixR = 0;
        int pixG = 0;
        int pixB = 0;
        int pixColor = 0;
        int newR = 0;
        int newG = 0;
        int newB = 0;
        int idx = 0;
        float alpha = 0.3F;
        int[] pixels = new int[width * height];
        mBitmapSrc.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                idx = 0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        pixColor = pixels[(i + n) * width + k + m];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);
                        newR = newR + (int) (pixR * laplacian[idx] * alpha);
                        newG = newG + (int) (pixG * laplacian[idx] * alpha);
                        newB = newB + (int) (pixB * laplacian[idx] * alpha);
                        idx++;
                    }
                }
                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                pixels[i * width + k] = Color.argb(255, newR, newG, newB);
                newR = 0;
                newG = 0;
                newB = 0;
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    */
/**
     * 图片复古
     *
     * @param mBitmapSrc 图片源
     * @return Bitmap
     *//*

    public Bitmap oldRemeberImage(Bitmap mBitmapSrc) {
      */
/*
       * 怀旧处理算法即设置新的RGB
       * R=0.393r+0.769g+0.189b
       * G=0.349r+0.686g+0.168b
       * B=0.272r+0.534g+0.131b
       *//*

        int width = mBitmapSrc.getWidth();
        int height = mBitmapSrc.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int pixColor = 0;
        int pixR = 0;
        int pixG = 0;
        int pixB = 0;
        int newR = 0;
        int newG = 0;
        int newB = 0;
        int[] pixels = new int[width * height];
        mBitmapSrc.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                pixColor = pixels[width * i + k];
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);
                newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);
                newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);
                int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);
                pixels[width * i + k] = newColor;
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    */
/**
     * 图像二值化处理
     *
     * @param mBitmapSrc 图片源
     * @return Bitmap
     *//*

    public Bitmap gray2Binary(Bitmap mBitmapSrc) {
        // 得到图形的宽度和长度
        int width = mBitmapSrc.getWidth();
        int height = mBitmapSrc.getHeight();
        // 创建二值化图像
        Bitmap binarybm = null;
        binarybm = mBitmapSrc.copy(Bitmap.Config.ARGB_8888, true);
        // 依次循环，对图像的像素进行处理
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // 得到当前像素的值
                int col = binarybm.getPixel(i, j);
                // 得到alpha通道的值
                int alpha = col & 0xFF000000;
                // 得到图像的像素RGB的值
                int red = (col & 0x00FF0000) >> 16;
                int green = (col & 0x0000FF00) >> 8;
                int blue = (col & 0x000000FF);
                // 用公式X = 0.3×R+0.59×G+0.11×B计算出X代替原来的RGB
                int gray = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
                // 对图像进行二值化处理
                if (gray <= 95) {
                    gray = 0;
                } else {
                    gray = 255;
                }
                // 新的ARGB
                int newColor = alpha | (gray << 16) | (gray << 8) | gray;
                // 设置新图像的当前像素值
                binarybm.setPixel(i, j, newColor);
            }
        }
        return binarybm;
    }



    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
*/
