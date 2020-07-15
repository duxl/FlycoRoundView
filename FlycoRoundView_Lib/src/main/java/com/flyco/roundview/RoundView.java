package com.flyco.roundview;

/**
 * TODO desc
 * create by duxl 2020/7/15
 */
public interface RoundView {

    RoundViewDelegate getDelegate();

    void onMeasure(int widthMeasureSpec, int heightMeasureSpec);

    void onLayout(boolean changed, int left, int top, int right, int bottom);
}
