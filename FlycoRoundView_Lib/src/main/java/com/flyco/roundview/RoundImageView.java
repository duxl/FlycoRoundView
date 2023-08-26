package com.flyco.roundview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 用于需要圆角矩形框背景的RoundImageView的情况,减少直接使用ImageView时引入的shape资源文件
 */
public class RoundImageView extends AppCompatImageView implements RoundView {
    private RoundViewDelegate delegate;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, com.google.android.material.R.attr.appBarLayoutStyle);
    }

    public RoundImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
}
