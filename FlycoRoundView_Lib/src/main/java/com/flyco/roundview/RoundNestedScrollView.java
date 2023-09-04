package com.flyco.roundview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.core.widget.NestedScrollView;

/**
 * 用于需要圆角矩形框背景的RoundNestedScrollView的情况,减少直接使用NestedScrollView时引入的shape资源文件
 * create by duxl 2023/2/25
 */
public class RoundNestedScrollView extends NestedScrollView implements RoundView {

    private RoundViewDelegate delegate;

    public RoundNestedScrollView(Context context) {
        this(context, null);
    }

    public RoundNestedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, androidx.core.R.attr.nestedScrollViewStyle);
    }

    public RoundNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
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

}
