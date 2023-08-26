package com.flyco.roundview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class RoundViewDelegate {
    private View view;
    private Context context;
    private GradientDrawable gd_background = new GradientDrawable();
    private GradientDrawable gd_background_press = new GradientDrawable();
    private GradientDrawable gd_background_disabled = new GradientDrawable();
    private int backgroundColor;
    private int backgroundDisabledColor;
    private int backgroundStartColor;
    private int backgroundEndColor;
    private int backgroundDisabledStartColor;
    private int backgroundDisabledEndColor;
    private int backgroundPressColor;
    private int backgroundPressStartColor;
    private int backgroundPressEndColor;
    private GradientDrawable.Orientation mOrientation;
    private int cornerRadius;
    private int cornerRadius_TL;
    private int cornerRadius_TR;
    private int cornerRadius_BL;
    private int cornerRadius_BR;
    private int strokeWidth;
    private int strokeColor;
    private int strokeDisabledColor;
    private int strokePressColor;
    private int textPressColor;
    private int textDisabledColor;
    private boolean isRadiusHalfHeight;
    private boolean isWidthHeightEqual;
    private boolean isRippleEnable;
    private float[] radiusArr = new float[8];

    public RoundViewDelegate(RoundView view, Context context, AttributeSet attrs) {
        this.view = (View) view;
        this.context = context;
        obtainAttributes(context, attrs);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundTextView);
        backgroundColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundColor, Color.TRANSPARENT);
        backgroundDisabledColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundDisabledColor, Color.TRANSPARENT);
        backgroundStartColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundStartColor, Integer.MAX_VALUE);
        backgroundEndColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundEndColor, Integer.MAX_VALUE);
        backgroundDisabledStartColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundDisabledStartColor, Integer.MAX_VALUE);
        backgroundDisabledEndColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundDisabledEndColor, Integer.MAX_VALUE);
        backgroundPressColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundPressColor, Integer.MAX_VALUE);
        backgroundPressStartColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundPressStartColor, Integer.MAX_VALUE);
        backgroundPressEndColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundPressEndColor, Integer.MAX_VALUE);
        float gradientAngle = ta.getFloat(R.styleable.RoundTextView_rv_gradient_angle, 0);
        setGradientAngle((int) gradientAngle);
        cornerRadius = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius, 0);
        strokeWidth = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_strokeWidth, 0);
        strokeColor = ta.getColor(R.styleable.RoundTextView_rv_strokeColor, Color.TRANSPARENT);
        strokeDisabledColor = ta.getColor(R.styleable.RoundTextView_rv_strokeDisabledColor, Color.TRANSPARENT);
        strokePressColor = ta.getColor(R.styleable.RoundTextView_rv_strokePressColor, Integer.MAX_VALUE);
        textPressColor = ta.getColor(R.styleable.RoundTextView_rv_textPressColor, Integer.MAX_VALUE);
        textDisabledColor = ta.getColor(R.styleable.RoundTextView_rv_textDisabledColor, Integer.MAX_VALUE);
        isRadiusHalfHeight = ta.getBoolean(R.styleable.RoundTextView_rv_isRadiusHalfHeight, false);
        isWidthHeightEqual = ta.getBoolean(R.styleable.RoundTextView_rv_isWidthHeightEqual, false);
        cornerRadius_TL = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TL, 0);
        cornerRadius_TR = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TR, 0);
        cornerRadius_BL = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BL, 0);
        cornerRadius_BR = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BR, 0);
        isRippleEnable = ta.getBoolean(R.styleable.RoundTextView_rv_isRippleEnable, true);

        ta.recycle();
    }

    public void enabledChange() {
        setBgSelector();
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        setBgSelector();
    }

    public void setBackgroundDisabledColor(int backgroundDisabledColor) {
        this.backgroundDisabledColor = backgroundDisabledColor;
        setBgSelector();
    }

    public void setBackgroundStartColor(int backgroundStartColor) {
        this.backgroundStartColor = backgroundStartColor;
        setBgSelector();
    }

    public void setBackgroundEndColor(int backgroundEndColor) {
        this.backgroundEndColor = backgroundEndColor;
        setBgSelector();
    }

    public void setBackgroundDisabledStartColor(int backgroundDisabledStartColor) {
        this.backgroundDisabledStartColor = backgroundDisabledStartColor;
        setBgSelector();
    }

    public void setBackgroundDisabledEndColor(int backgroundDisabledEndColor) {
        this.backgroundDisabledEndColor = backgroundDisabledEndColor;
        setBgSelector();
    }

    public void setBackgroundPressColor(int backgroundPressColor) {
        this.backgroundPressColor = backgroundPressColor;
        setBgSelector();
    }

    public void setBackgroundPressStartColor(int backgroundPressStartColor) {
        this.backgroundPressStartColor = backgroundPressStartColor;
        setBgSelector();
    }

    public void setBackgroundPressEndColor(int backgroundPressEndColor) {
        this.backgroundPressEndColor = backgroundPressEndColor;
        setBgSelector();
    }

    public void setOrientation(GradientDrawable.Orientation orientation) {
        this.mOrientation = orientation;
        setBgSelector();
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = dp2px(cornerRadius);
        setBgSelector();
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = dp2px(strokeWidth);
        setBgSelector();
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        setBgSelector();
    }

    public void setStrokeDisabledColor(int strokeDisabledColor) {
        this.strokeDisabledColor = strokeDisabledColor;
        setBgSelector();
    }

    public void setStrokePressColor(int strokePressColor) {
        this.strokePressColor = strokePressColor;
        setBgSelector();
    }

    public void setTextPressColor(int textPressColor) {
        this.textPressColor = textPressColor;
        setBgSelector();
    }

    public void setTextDisabledColor(int textDisabledColor) {
        this.textDisabledColor = textDisabledColor;
        setBgSelector();
    }

    public GradientDrawable.Orientation getOrientation() {
        return mOrientation;
    }

    public void setIsRadiusHalfHeight(boolean isRadiusHalfHeight) {
        this.isRadiusHalfHeight = isRadiusHalfHeight;
        setBgSelector();
    }

    public void setIsWidthHeightEqual(boolean isWidthHeightEqual) {
        this.isWidthHeightEqual = isWidthHeightEqual;
        setBgSelector();
    }

    public void setCornerRadius_TL(int cornerRadius_TL) {
        this.cornerRadius_TL = cornerRadius_TL;
        setBgSelector();
    }

    public void setCornerRadius_TR(int cornerRadius_TR) {
        this.cornerRadius_TR = cornerRadius_TR;
        setBgSelector();
    }

    public void setCornerRadius_BL(int cornerRadius_BL) {
        this.cornerRadius_BL = cornerRadius_BL;
        setBgSelector();
    }

    public void setCornerRadius_BR(int cornerRadius_BR) {
        this.cornerRadius_BR = cornerRadius_BR;
        setBgSelector();
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getBackgroundDisabledColor() {
        return backgroundDisabledColor;
    }

    public int getBackgroundStartColor() {
        return backgroundStartColor;
    }

    public int getBackgroundDisabledStartColor() {
        return backgroundDisabledStartColor;
    }

    public int getBackgroundEndColor() {
        return backgroundEndColor;
    }

    public int getBackgroundDisabledEndColor() {
        return backgroundDisabledEndColor;
    }

    public int getBackgroundPressColor() {
        return backgroundPressColor;
    }

    public int getBackgroundPressStartColor() {
        return backgroundPressStartColor;
    }

    public int getBackgroundPressEndColor() {
        return backgroundPressEndColor;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public int getStrokeDisabledColor() {
        return strokeDisabledColor;
    }

    public int getStrokePressColor() {
        return strokePressColor;
    }

    public int getTextPressColor() {
        return textPressColor;
    }

    public int getTextDisabledColor() {
        return textDisabledColor;
    }

    public boolean isRadiusHalfHeight() {
        return isRadiusHalfHeight;
    }

    public boolean isWidthHeightEqual() {
        return isWidthHeightEqual;
    }

    public int getCornerRadius_TL() {
        return cornerRadius_TL;
    }

    public int getCornerRadius_TR() {
        return cornerRadius_TR;
    }

    public int getCornerRadius_BL() {
        return cornerRadius_BL;
    }

    public int getCornerRadius_BR() {
        return cornerRadius_BR;
    }

    protected int dp2px(float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        final float scale = this.context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    private void setDrawable(GradientDrawable gd, int color, int startColor, int endColor, int strokeColor) {
        if (startColor != Integer.MAX_VALUE && endColor != Integer.MAX_VALUE) {
            gd.setColors(new int[]{startColor, endColor});
        } else {
            gd.setColor(color);
        }

        gd.setOrientation(mOrientation);

        if (cornerRadius_TL > 0 || cornerRadius_TR > 0 || cornerRadius_BR > 0 || cornerRadius_BL > 0) {
            /**The corners are ordered top-left, top-right, bottom-right, bottom-left*/
            radiusArr[0] = cornerRadius_TL;
            radiusArr[1] = cornerRadius_TL;
            radiusArr[2] = cornerRadius_TR;
            radiusArr[3] = cornerRadius_TR;
            radiusArr[4] = cornerRadius_BR;
            radiusArr[5] = cornerRadius_BR;
            radiusArr[6] = cornerRadius_BL;
            radiusArr[7] = cornerRadius_BL;
            gd.setCornerRadii(radiusArr);
        } else {
            gd.setCornerRadius(cornerRadius);
        }

        gd.setStroke(strokeWidth, strokeColor);
    }

    public void setBgSelector() {
        StateListDrawable bg = new StateListDrawable();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && isRippleEnable && view.isEnabled()) {
            setDrawable(gd_background, backgroundColor, backgroundStartColor, backgroundEndColor, strokeColor);
            RippleDrawable rippleDrawable = new RippleDrawable(getPressedColorSelector(backgroundColor, backgroundPressColor), gd_background, null);
            view.setBackground(rippleDrawable);
            System.out.println("--set111111111");
        } else {
            System.out.println("--set22222222" + view.isEnabled());
            // 设置按压状态背景色
            if (backgroundPressColor != Integer.MAX_VALUE || strokePressColor != Integer.MAX_VALUE) {
                setDrawable(gd_background_press,
                        backgroundPressColor == Integer.MAX_VALUE ? backgroundColor : backgroundPressColor,
                        backgroundPressStartColor == Integer.MAX_VALUE ? backgroundStartColor : backgroundPressStartColor,
                        backgroundPressEndColor == Integer.MAX_VALUE ? backgroundEndColor : backgroundPressEndColor,
                        strokePressColor == Integer.MAX_VALUE ? strokeColor : strokePressColor);
                bg.addState(new int[]{android.R.attr.state_pressed}, gd_background_press);
            }
            // 设置不可用状态背景色
            if (backgroundDisabledColor != Integer.MAX_VALUE || strokeDisabledColor != Integer.MAX_VALUE) {
                setDrawable(gd_background_disabled,
                        backgroundDisabledColor == Integer.MAX_VALUE ? backgroundColor : backgroundDisabledColor,
                        backgroundDisabledStartColor == Integer.MAX_VALUE ? backgroundStartColor : backgroundDisabledStartColor,
                        backgroundDisabledEndColor == Integer.MAX_VALUE ? backgroundEndColor : backgroundDisabledEndColor,
                        strokeDisabledColor == Integer.MAX_VALUE ? strokeColor : strokeDisabledColor);
                bg.addState(new int[]{-android.R.attr.state_enabled}, gd_background_disabled);
            }

            // 设置默认状态背景色
            setDrawable(gd_background, backgroundColor, backgroundStartColor, backgroundEndColor, strokeColor);
            bg.addState(new int[]{-android.R.attr.state_pressed}, gd_background);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {//16
                view.setBackground(bg);
            } else {
                //noinspection deprecation
                view.setBackgroundDrawable(bg);
            }
        }

        if (view instanceof TextView) {
            ColorStateList textColors = ((TextView) view).getTextColors();
//              Log.d("AAA", textColors.getColorForState(new int[]{-android.R.attr.state_pressed}, -1) + "");
            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_pressed},
                            new int[]{-android.R.attr.state_enabled},
                            new int[]{}
                    },
                    new int[]{
                            textPressColor != Integer.MAX_VALUE ? textPressColor : textColors.getDefaultColor(),
                            textDisabledColor != Integer.MAX_VALUE ? textDisabledColor : textColors.getDefaultColor(),
                            textColors.getDefaultColor()
                    });
            ((TextView) view).setTextColor(colorStateList);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private ColorStateList getPressedColorSelector(int normalColor, int pressedColor) {
        return new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_activated},
                        new int[]{}
                },
                new int[]{
                        pressedColor,
                        pressedColor,
                        pressedColor,
                        normalColor
                }
        );
    }

    public void setGradientAngle(int angle) {
        if (angle < 0 || angle > 360) {
            angle = 0;
        }

        angle = angle / 45 * 45;
        switch (angle) {
            case 0:
                mOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case 45:
                mOrientation = GradientDrawable.Orientation.BL_TR;
                break;
            case 90:
                mOrientation = GradientDrawable.Orientation.BOTTOM_TOP;
                break;
            case 135:
                mOrientation = GradientDrawable.Orientation.BR_TL;
                break;
            case 180:
                mOrientation = GradientDrawable.Orientation.RIGHT_LEFT;
                break;
            case 225:
                mOrientation = GradientDrawable.Orientation.TR_BL;
                break;
            case 270:
                mOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
            case 315:
                mOrientation = GradientDrawable.Orientation.TL_BR;
                break;
        }
    }

    public int measure(int measureSpec) {
        if (isWidthHeightEqual() && view.getWidth() > 0 && view.getHeight() > 0) {
            int max = Math.max(view.getWidth(), view.getHeight());
            return View.MeasureSpec.makeMeasureSpec(max, View.MeasureSpec.EXACTLY);
        }
        return measureSpec;
    }

    public void onLayout() {
        if (isRadiusHalfHeight()) {
            setCornerRadius(view.getHeight() / 2);
        } else {
            setBgSelector();
        }
    }
}
