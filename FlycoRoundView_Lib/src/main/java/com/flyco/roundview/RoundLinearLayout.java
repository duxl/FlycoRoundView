package com.flyco.roundview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 用于需要圆角矩形框背景的LinearLayout的情况,减少直接使用LinearLayout时引入的shape资源文件
 */
public class RoundLinearLayout extends LinearLayout implements RoundView {
    private RoundViewDelegate delegate;

    public RoundLinearLayout(Context context) {
        this(context, null);
    }

    public RoundLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
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
}
