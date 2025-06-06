package com.flyco.roundview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
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
    private GradientDrawable gd_background_selected = new GradientDrawable();
    // 默认状态
    private int backgroundColor;
    private int backgroundStartColor;
    private int backgroundEndColor;
    // 不可用状态
    private int backgroundDisabledColor;
    private int backgroundDisabledStartColor;
    private int backgroundDisabledEndColor;
    // 选中状态
    private int backgroundSelectedColor;
    private int backgroundSelectedStartColor;
    private int backgroundSelectedEndColor;
    // 按压状态
    private int backgroundPressColor;
    private int backgroundPressStartColor;
    private int backgroundPressEndColor;

    // 渐变色方向
    private GradientDrawable.Orientation mOrientation;
    // 4个角大小
    private int cornerRadius;
    private int cornerRadius_TL;
    private int cornerRadius_TR;
    private int cornerRadius_BL;
    private int cornerRadius_BR;
    // 描边
    private int strokeWidth;
    private int strokePressWidth;
    private int strokeDisabledWidth;
    private int strokeSelectedWidth;
    private int strokeColor;
    private int strokeDisabledColor;
    private int strokePressColor;
    private int strokeSelectedColor;
    // 文字
    private int textPressColor;
    private int textDisabledColor;
    private int textSelectedColor;

    private boolean isRadiusHalfHeight;
    private boolean isWidthHeightEqual;

    private boolean isRippleEnable;
    private float[] radiusArr = new float[8];

    // 阴影
    private Paint shadowPaint;
    private float shadowDx; // 水平偏移量
    private float shadowDy; // 垂直偏移量
    private float shadowRadius; // 阴影半径
    private int shadowColor; // 阴影颜色

    private boolean isSelected;

    public RoundViewDelegate(RoundView view, Context context, AttributeSet attrs) {
        this.view = (View) view;
        this.context = context;
        obtainAttributes(context, attrs);
        this.view.setSelected(isSelected);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundTextView);
        // 默认状态背景
        backgroundColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundColor, Integer.MAX_VALUE);
        backgroundStartColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundStartColor, Integer.MAX_VALUE);
        backgroundEndColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundEndColor, Integer.MAX_VALUE);
        // 不可用状态背景
        backgroundDisabledColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundDisabledColor, Integer.MAX_VALUE);
        backgroundDisabledStartColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundDisabledStartColor, Integer.MAX_VALUE);
        backgroundDisabledEndColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundDisabledEndColor, Integer.MAX_VALUE);
        // 按压状态背景
        backgroundPressColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundPressColor, Integer.MAX_VALUE);
        backgroundPressStartColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundPressStartColor, Integer.MAX_VALUE);
        backgroundPressEndColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundPressEndColor, Integer.MAX_VALUE);
        // 选中状态背景
        backgroundSelectedColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundSelectedColor, Integer.MAX_VALUE);
        backgroundSelectedStartColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundSelectedStartColor, Integer.MAX_VALUE);
        backgroundSelectedEndColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundSelectedEndColor, Integer.MAX_VALUE);

        // 渐变色方向
        float gradientAngle = ta.getFloat(R.styleable.RoundTextView_rv_gradient_angle, 0);
        setGradientAngle((int) gradientAngle);

        // 4个角大小
        cornerRadius = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius, 0);
        cornerRadius_TL = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TL, 0);
        cornerRadius_TR = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TR, 0);
        cornerRadius_BL = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BL, 0);
        cornerRadius_BR = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BR, 0);

        // 描边
        strokeWidth = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_strokeWidth, 0);
        strokePressWidth = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_strokePressWidth, 0);
        strokeDisabledWidth = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_strokeDisabledWidth, 0);
        strokeSelectedWidth = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_strokeSelectedWidth, 0);
        strokeColor = ta.getColor(R.styleable.RoundTextView_rv_strokeColor, Integer.MAX_VALUE);
        strokeDisabledColor = ta.getColor(R.styleable.RoundTextView_rv_strokeDisabledColor, Integer.MAX_VALUE);
        strokePressColor = ta.getColor(R.styleable.RoundTextView_rv_strokePressColor, Integer.MAX_VALUE);
        strokeSelectedColor = ta.getColor(R.styleable.RoundTextView_rv_strokeSelectedColor, Integer.MAX_VALUE);

        // 文字颜色
        textPressColor = ta.getColor(R.styleable.RoundTextView_rv_textPressColor, Integer.MAX_VALUE);
        textDisabledColor = ta.getColor(R.styleable.RoundTextView_rv_textDisabledColor, Integer.MAX_VALUE);
        textSelectedColor = ta.getColor(R.styleable.RoundTextView_rv_textSelectedColor, Integer.MAX_VALUE);

        isRadiusHalfHeight = ta.getBoolean(R.styleable.RoundTextView_rv_isRadiusHalfHeight, false);
        isWidthHeightEqual = ta.getBoolean(R.styleable.RoundTextView_rv_isWidthHeightEqual, false);
        isRippleEnable = ta.getBoolean(R.styleable.RoundTextView_rv_isRippleEnable, true);

        // 阴影
        shadowDx = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_shadowDx, 0); // 水平偏移量
        shadowDy = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_shadowDy, 0); // 垂直偏移量
        shadowRadius = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_shadowRadius, 0); // 阴影半径
        shadowColor = ta.getColor(R.styleable.RoundTextView_rv_shadowColor, Color.TRANSPARENT); // 阴影颜色

        // 是否选中
        isSelected = ta.getBoolean(R.styleable.RoundTextView_rv_isSelected, false);

        ta.recycle();
    }

    public void enabledChange() {
        setBgSelector();
    }

    public void selectedChange() {
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

    public void setBackgroundSelectedColor(int backgroundSelectedColor) {
        this.backgroundSelectedColor = backgroundSelectedColor;
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

    public void setBackgroundSelectedStartColor(int backgroundSelectedStartColor) {
        this.backgroundSelectedStartColor = backgroundSelectedStartColor;
    }

    public void setBackgroundSelectedEndColor(int backgroundSelectedEndColor) {
        this.backgroundSelectedEndColor = backgroundSelectedEndColor;
    }

    public void setStrokeSelectedColor(int strokeSelectedColor) {
        this.strokeSelectedColor = strokeSelectedColor;
    }

    public void setTextSelectedColor(int textSelectedColor) {
        this.textSelectedColor = textSelectedColor;
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
        this.cornerRadius = cornerRadius;
        setBgSelector();
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        setBgSelector();
    }

    public void setStrokePressWidth(int strokePressWidth) {
        this.strokePressWidth = strokePressWidth;
    }

    public void setStrokeDisabledWidth(int strokeDisabledWidth) {
        this.strokeDisabledWidth = strokeDisabledWidth;
    }

    public void setStrokeSelectedWidth(int strokeSelectedWidth) {
        this.strokeSelectedWidth = strokeSelectedWidth;
    }

    public int getStrokePressWidth() {
        return strokePressWidth;
    }

    public int getStrokeDisabledWidth() {
        return strokeDisabledWidth;
    }

    public int getStrokeSelectedWidth() {
        return strokeSelectedWidth;
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

    public int getBackgroundSelectedColor() {
        return backgroundSelectedColor;
    }

    public int getBackgroundSelectedStartColor() {
        return backgroundSelectedStartColor;
    }

    public int getBackgroundSelectedEndColor() {
        return backgroundSelectedEndColor;
    }

    public int getStrokeSelectedColor() {
        return strokeSelectedColor;
    }

    public int getTextSelectedColor() {
        return textSelectedColor;
    }

    public void setShadow(float radius, float dx, float dy, int color) {
        this.shadowRadius = radius;
        this.shadowDx = dx;
        this.shadowDy = dy;
        this.shadowColor = color;
        view.invalidate();
    }

    public float getShadowDx() {
        return shadowDx;
    }

    public float getShadowDy() {
        return shadowDy;
    }

    public float getShadowRadius() {
        return shadowRadius;
    }

    public int getShadowColor() {
        return shadowColor;
    }

    /*protected int dp2px(float dp) {
        if (dp == 0) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        if (sp == 0) {
            return 0;
        }
        final float scale = this.context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }*/

    private void setDrawable(GradientDrawable gd, int color, int startColor, int endColor, int strokeWidth, int strokeColor) {
        if (startColor != Integer.MAX_VALUE && endColor != Integer.MAX_VALUE) {
            gd.setColors(new int[]{startColor, endColor});
        } else if (color != Integer.MAX_VALUE) {
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

        if (strokeWidth > 0 && strokeColor != Integer.MAX_VALUE) {
            gd.setStroke(strokeWidth, strokeColor);
        }
    }

    public void setBgSelector() {
        StateListDrawable bg = new StateListDrawable();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && isRippleEnable && view.isEnabled()) {
            setDrawable(gd_background, backgroundColor, backgroundStartColor, backgroundEndColor, strokeWidth, strokeColor);
            GradientDrawable background = gd_background;
            if (view.isSelected() && hasSetColor(backgroundSelectedColor, backgroundSelectedStartColor, backgroundSelectedEndColor, strokeSelectedColor)) {
                setDrawable(gd_background_selected,
                        backgroundSelectedColor,
                        backgroundSelectedStartColor,
                        backgroundSelectedEndColor,
                        strokeSelectedWidth,
                        strokeSelectedColor);
                background = gd_background_selected;
            }
            //Drawable mask = new ColorDrawable(Color.BLUE);
            Drawable mask = null;
            RippleDrawable rippleDrawable = new RippleDrawable(getPressedColorSelector(backgroundColor, backgroundPressColor, backgroundSelectedColor), background, mask);
            view.setBackground(rippleDrawable);
        } else {
            // 设置按压状态背景色
            if (hasSetColor(backgroundPressColor, backgroundPressStartColor, backgroundPressEndColor, strokePressColor)) {
                setDrawable(gd_background_press,
                        backgroundPressColor,
                        backgroundPressStartColor,
                        backgroundPressEndColor,
                        strokePressWidth,
                        strokePressColor);
                bg.addState(new int[]{android.R.attr.state_pressed}, gd_background_press);
            }
            // 设置不可用状态背景色
            if (hasSetColor(backgroundDisabledColor, backgroundDisabledStartColor, backgroundDisabledEndColor, strokeDisabledColor)) {
                setDrawable(gd_background_disabled,
                        backgroundDisabledColor,
                        backgroundDisabledStartColor,
                        backgroundDisabledEndColor,
                        strokeDisabledWidth,
                        strokeDisabledColor);
                bg.addState(new int[]{-android.R.attr.state_enabled}, gd_background_disabled);
            }
            // 设置选中状态背景色
            if (hasSetColor(backgroundSelectedColor, backgroundSelectedStartColor, backgroundSelectedEndColor, strokeSelectedColor)) {
                setDrawable(gd_background_selected,
                        backgroundSelectedColor,
                        backgroundSelectedStartColor,
                        backgroundSelectedEndColor,
                        strokeSelectedWidth,
                        strokeSelectedColor);
                bg.addState(new int[]{android.R.attr.state_selected}, gd_background_selected);
            }

            // 设置默认状态背景色
            setDrawable(gd_background, backgroundColor, backgroundStartColor, backgroundEndColor, strokeWidth, strokeColor);
            bg.addState(new int[]{}, gd_background);

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
                            new int[]{android.R.attr.state_selected},
                            new int[]{}
                    },
                    new int[]{
                            textPressColor != Integer.MAX_VALUE ? textPressColor : textColors.getDefaultColor(),
                            textDisabledColor != Integer.MAX_VALUE ? textDisabledColor : textColors.getDefaultColor(),
                            textSelectedColor != Integer.MAX_VALUE ? textSelectedColor : textColors.getDefaultColor(),
                            textColors.getDefaultColor()
                    });
            ((TextView) view).setTextColor(colorStateList);
        }
    }

    private boolean hasSetColor(int bgColor, int bgStartColor, int bgEndColor, int strokeColor) {
        return bgColor != Integer.MAX_VALUE
                || bgStartColor != Integer.MAX_VALUE
                || bgEndColor != Integer.MAX_VALUE
                || strokeColor != Integer.MAX_VALUE;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private ColorStateList getPressedColorSelector(int normalColor, int pressedColor, int selectedColor) {
        return new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_activated},
                        new int[]{android.R.attr.state_selected},
                        new int[]{}
                },
                new int[]{
                        pressedColor,
                        pressedColor,
                        pressedColor,
                        selectedColor,
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

    public void drawShadow(Canvas canvas) {
        if (shadowDx == 0 && shadowDy == 0) {
            return;
        }
        Path shadowPath = new Path();
        int width = view.getWidth();
        int height = view.getHeight();
        if (cornerRadius_TL > 0 || cornerRadius_TR > 0 || cornerRadius_BR > 0 || cornerRadius_BL > 0) {
            shadowPath.moveTo(cornerRadius_TL, 0);
            shadowPath.lineTo(width - cornerRadius_TR, 0);
            shadowPath.arcTo(width - cornerRadius_TR * 2, 0, width, cornerRadius_TR * 2, 270, 90, false);
            shadowPath.lineTo(width, height - cornerRadius_BR);
            shadowPath.arcTo(width - cornerRadius_BR * 2, height - cornerRadius_BR * 2, width, height, 0, 90, false);
            shadowPath.lineTo(cornerRadius_BL, height);
            shadowPath.arcTo(0, height - cornerRadius_BL * 2, cornerRadius_BL * 2, height, 90, 90, false);
            shadowPath.lineTo(0, cornerRadius_TL);
            shadowPath.arcTo(0, 0, cornerRadius_TL * 2, cornerRadius_TL * 2, 180, 90, false);
            shadowPath.close();

            radiusArr[0] = cornerRadius_TL;
            radiusArr[1] = cornerRadius_TL;
            radiusArr[2] = cornerRadius_TR;
            radiusArr[3] = cornerRadius_TR;
            radiusArr[4] = cornerRadius_BR;
            radiusArr[5] = cornerRadius_BR;
            radiusArr[6] = cornerRadius_BL;
            radiusArr[7] = cornerRadius_BL;
        } else {
            RectF rect = new RectF(0, 0, width, height);
            shadowPath.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CCW);
        }

        if (shadowPaint == null) {
            shadowPaint = new Paint();
            shadowPaint.setAntiAlias(true);
            shadowPaint.setStyle(Paint.Style.FILL);
        }

        canvas.save();
        canvas.clipOutPath(shadowPath);
        shadowPaint.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
        canvas.drawPath(shadowPath, shadowPaint);

        canvas.restore();
    }
}
