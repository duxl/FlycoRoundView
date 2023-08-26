package com.flyco.roundview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;


/**
 * 用于需要圆角矩形框背景的EmptyRoundView的情况,减少直接使用View时引入的shape资源文件
 * create by duxl 2023/2/25
 */
public class RoundEmptyView extends View implements RoundView {

    private RoundViewDelegate delegate;

    public RoundEmptyView(Context context) {
        this(context, null);
    }

    public RoundEmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundEmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new RoundViewDelegate(this, context, attrs);
    }

    @Override
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
