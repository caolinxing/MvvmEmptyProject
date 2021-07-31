package com.zhulong.common.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/**
 * glide加载圆形图片的实现
 */
public class GlideCircleTransform extends BitmapTransformation {
    private Context context;
    private int mBorderColor;
    private int mBorderWidth;
    Paint mBorderPaint;
    //id 必须为this.getClass().getName(); 唯一id
    private String ID = this.getClass().getName();
    private byte[] ID_BYTES = ID.getBytes(CHARSET);

    public GlideCircleTransform(Context context) {
        super();
        this.context = context;
    }

    /**
     * @param context
     * @param bordColor 边框颜色
     * @param bordWidth 边框宽度
     */
    public GlideCircleTransform(Context context, int bordColor, int bordWidth) {
        super();
        this.context = context;
        this.mBorderColor = bordColor;
        this.mBorderWidth = bordWidth;
        mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        //代码里默认px
        mBorderPaint.setStrokeWidth(mBorderWidth);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private Bitmap circleCrop(BitmapPool pool, Bitmap source) {

        int size = Math.min(source.getWidth() - mBorderWidth * 2, source.getHeight() - mBorderWidth * 2);

        int width = (source.getWidth() - size) / 2;
        int height = (source.getHeight() - size) / 2;

        Bitmap bitmap = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG, Paint.FILTER_BITMAP_FLAG));
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        if (width != 0 || height != 0) {
            Matrix matrix = new Matrix();
            matrix.setTranslate(-width, -height);
            shader.setLocalMatrix(matrix);
        }
        paint.setShader(shader);
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        if (mBorderWidth > 0) {
            int mBorderRadius = (size - mBorderWidth) / 2;
            canvas.drawCircle(r, r, mBorderRadius, mBorderPaint);
        }
        return bitmap;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof GlideCircleTransform) {
            GlideCircleTransform circleTransform = (GlideCircleTransform) o;
            /*其实这里可以直接返回true仿照官网写。。。做个比较混淆一下视听*/
            return (mBorderColor == circleTransform.mBorderColor)
                    && (mBorderWidth == circleTransform.mBorderWidth);
        }
        return false;
    }

    @Override
    public int hashCode() {
        /*Util.hashCode 只能有两个参数 所以在这里把自己定义的参数合并成一个。。有更好的办法欢迎评论*/
        return Util.hashCode(ID.hashCode(),
                Util.hashCode(Util.hashCode(mBorderWidth),
                        Util.hashCode(mBorderColor))
        );
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
        /*在这里把自己的参数也给更新一下 不然会产生某些奇怪的效果 比如宽度设置没反应*/
        byte[] color = ByteBuffer.allocate(4).putInt(mBorderColor).array();
        messageDigest.update(color);
        byte[] width = ByteBuffer.allocate(4).putInt(mBorderWidth).array();
        messageDigest.update(width);
    }
}