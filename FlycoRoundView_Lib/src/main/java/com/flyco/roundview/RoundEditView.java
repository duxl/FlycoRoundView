package com.flyco.roundview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 用于需要圆角矩形框背景的EditText的情况,减少直接使用EditText时引入的shape资源文件
 */
public class RoundEditView extends EditText implements RoundView {
    private RoundViewDelegate delegate;

    public RoundEditView(Context context) {
        this(context, null);
    }

    public RoundEditView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public RoundEditView(Context context, AttributeSet attrs, int defStyleAttr) {
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
}
