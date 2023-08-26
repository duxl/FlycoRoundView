package com.flyco.roundview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import androidx.core.widget.NestedScrollView;

/**
 * 用于需要圆角矩形框背景的RoundRadioGroup的情况,减少直接使用RadioGroup时引入的shape资源文件
 * create by duxl 2023/2/25
 */
public class RoundRadioGroup extends RadioGroup implements RoundView {

    private RoundViewDelegate delegate;

    public RoundRadioGroup(Context context) {
        this(context, null);
    }

    public RoundRadioGroup(Context context, AttributeSet attrs) {
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

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(delegate != null) {
            delegate.enabledChange();
        }
    }
}
