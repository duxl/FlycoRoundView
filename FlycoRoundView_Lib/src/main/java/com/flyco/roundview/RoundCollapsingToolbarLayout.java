package com.flyco.roundview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * 用于需要圆角矩形框背景的RoundCollapsingToolbarLayout的情况,减少直接使用CollapsingToolbarLayout时引入的shape资源文件
 */
public class RoundCollapsingToolbarLayout extends CollapsingToolbarLayout implements RoundView {
    private RoundViewDelegate delegate;

    public RoundCollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public RoundCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, com.google.android.material.R.attr.collapsingToolbarLayoutStyle);
    }

    public RoundCollapsingToolbarLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new RoundViewDelegate(this, context, attrs);
    }

    /**
     * use delegate to set attr
     */
    public RoundViewDelegate getDelegate() {
        return delegate;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(delegate.measure(widthMeasureSpec), delegate.measure(heightMeasureSpec));
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        delegate.onLayout();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(delegate != null) {
            delegate.enabledChange();
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if(delegate != null) {
            delegate.selectedChange();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(delegate != null) {
            delegate.drawShadow(canvas);
        }
        super.draw(canvas);
    }
}
