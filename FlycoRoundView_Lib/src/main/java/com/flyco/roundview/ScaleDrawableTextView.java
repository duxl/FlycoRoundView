package com.flyco.roundview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * 可在xml中指定drawable大小的TextView
 */
public class ScaleDrawableTextView extends RoundTextView {

    private int drawableStartWidth = -1;
    private int drawableStartHeight = -1;

    private int drawableEndWidth = -1;
    private int drawableEndHeight = -1;

    private int drawableTopWidth = -1;
    private int drawableTopHeight = -1;

    private int drawableBottomWidth = -1;
    private int drawableBottomHeight = -1;

    public ScaleDrawableTextView(Context context) {
        this(context, null);
    }

    public ScaleDrawableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleDrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray typeArr = context.obtainStyledAttributes(attrs, R.styleable.ScaleDrawableTextView);
            getSize(typeArr, R.styleable.ScaleDrawableTextView_drawableSize, drawableSize -> {
                drawableStartWidth = drawableSize;
                drawableEndWidth = drawableSize;
                drawableTopWidth = drawableSize;
                drawableBottomWidth = drawableSize;

                drawableStartHeight = drawableSize;
                drawableEndHeight = drawableSize;
                drawableTopHeight = drawableSize;
                drawableBottomHeight = drawableSize;
            });

            getSize(typeArr, R.styleable.ScaleDrawableTextView_drawableWidth, drawableWidth -> {
                drawableStartWidth = drawableWidth;
                drawableEndWidth = drawableWidth;
                drawableTopWidth = drawableWidth;
                drawableBottomWidth = drawableWidth;
            });

            getSize(typeArr, R.styleable.ScaleDrawableTextView_drawableHeight, drawableHeight -> {
                drawableStartHeight = drawableHeight;
                drawableEndHeight = drawableHeight;
                drawableTopHeight = drawableHeight;
                drawableBottomHeight = drawableHeight;
            });

            getSize(typeArr, R.styleable.ScaleDrawableTextView_drawableStartWidth, startWidth -> drawableStartWidth = startWidth);
            getSize(typeArr, R.styleable.ScaleDrawableTextView_drawableStartHeight, startHeight -> drawableStartHeight = startHeight);

            getSize(typeArr, R.styleable.ScaleDrawableTextView_drawableEndWidth, endWidth -> drawableEndWidth = endWidth);
            getSize(typeArr, R.styleable.ScaleDrawableTextView_drawableEndHeight, endHeight -> drawableEndHeight = endHeight);

            getSize(typeArr, R.styleable.ScaleDrawableTextView_drawableTopWidth, topWidth -> drawableTopWidth = topWidth);
            getSize(typeArr, R.styleable.ScaleDrawableTextView_drawableTopHeight, topHeight -> drawableTopHeight = topHeight);

            getSize(typeArr, R.styleable.ScaleDrawableTextView_drawableBottomWidth, bottomWidth -> drawableBottomWidth = bottomWidth);
            getSize(typeArr, R.styleable.ScaleDrawableTextView_drawableBottomHeight, bottomHeight -> drawableBottomHeight = bottomHeight);

            typeArr.recycle();
        }

        resizeDrawables();
    }

    private void getSize(
            TypedArray typedArray,
            int attrIndex,
            OnHasValueCallback callback
    ) {
        if (typedArray.hasValue(attrIndex)) {
            int value = typedArray.getDimensionPixelSize(attrIndex, -1);
            if (value != -1) {
                callback.onHasValue(value);
            }
        }
    }

    private interface OnHasValueCallback {
        void onHasValue(int value);
    }

    public void setDrawableSize(int width, int height) {
        drawableStartWidth = width;
        drawableStartHeight = height;

        drawableEndWidth = width;
        drawableEndHeight = height;

        drawableTopWidth = width;
        drawableTopHeight = height;

        drawableBottomWidth = width;
        drawableBottomHeight = height;

        resizeDrawables();
    }

    public void setStartDrawableSize(int width, int height) {
        drawableStartWidth = width;
        drawableStartHeight = height;

        resizeDrawables();
    }

    public void setEndDrawableSize(int width, int height) {
        drawableEndWidth = width;
        drawableEndHeight = height;

        resizeDrawables();
    }

    public void setTopDrawableSize(int width, int height) {
        drawableTopWidth = width;
        drawableTopHeight = height;

        resizeDrawables();
    }

    public void setBottomDrawableSize(int width, int height) {
        drawableBottomWidth = width;
        drawableBottomHeight = height;

        resizeDrawables();
    }

    private void resizeDrawables() {
        Drawable[] drawables = getCompoundDrawablesRelative();
        setCompoundDrawablesRelative(
                resize(drawables[0], drawableStartWidth, drawableStartHeight),
                resize(drawables[1], drawableTopWidth, drawableTopHeight),
                resize(drawables[2], drawableEndWidth, drawableEndHeight),
                resize(drawables[3], drawableBottomWidth, drawableBottomHeight)
        );
    }

    private Drawable resize(Drawable drawable, int w, int h) {
        if (drawable == null) return null;
        if (w == -1 || h == -1) return drawable;

        Drawable newDrawable = drawable.mutate();
        newDrawable.setBounds(0, 0, w, h);
        return newDrawable;
    }
}
