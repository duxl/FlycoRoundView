### FlycoRoundView

一个扩展原生控件支持圆角矩形框背景的库,可以减少相关shape资源文件使用.

扩展的Views：

```java
com.flyco.roundview.RoundTextView
```

```java
com.flyco.roundview.RoundEditView
```

```java
com.flyco.roundview.RoundLinearLayout
```

```java
com.flyco.roundview.RoundRelativeLayout
```

```java
com.flyco.roundview.RoundFrameLayout
```

```java
com.flyco.roundview.RoundConstraintLayout
```

```java
com.flyco.roundview.RoundEmptyView
```

```java
com.flyco.roundview.RoundRecyclerView
```

```java
com.flyco.roundview.RoundRadioGroup
```

```java
com.flyco.roundview.RoundNestedScrollView
```

```java
com.flyco.roundview.RoundCoordinatorLayout
```

```java
com.flyco.roundview.RoundAppBarLayout
```

```java
com.flyco.roundview.RoundCollapsingToolbarLayout
```



### Demo

![preview](https://github.com/duxl/FlycoRoundView/blob/master/preview.gif)

### Gradle

```groovy
dependencies{
    compile 'com.github.duxl:FlycoRoundView:v4.0.0'
}
```

### 属性

| 属性                            | 类型      | 描述                                                      |
| ------------------------------ | --------- | -------------------------------------------------------- |
| rv_backgroundColor             | color     | 背景色                                                    |
| rv_backgroundDisabledColor     | color     | 背景不可用状态色                                            |
| rv_backgroundStartColor        | color     | 背景渐变开始色                                              |
| rv_backgroundEndColor          | color     | 背景渐变结束色                                              |
| rv_backgroundDisabledStartColor| color     | 背景不可用状态渐变开始色                                     |
| rv_backgroundDisabledEndColor  | color     | 背景不可用状态渐变结束色                                     |
| rv_backgroundPressColor        | color     | 背景按压色                                                 |
| rv_backgroundPressStartColor   | color     | 背景按压渐变开始色                                          |
| rv_backgroundPressEndColor     | color     | 背景按压渐变结束色                                          |
| rv_gradient_angle              | float     | 渐变角度，仅与线性渐变一起使用。必须是45的倍数 范围[0，315]      |
| rv_cornerRadius                | dimension | 边框弧度,单位dp                                            |
| rv_strokeWidth                 | dimension | 边框宽度,单位dp                                            |
| rv_strokeColor                 | color     | 边框颜色                                                  |
| rv_strokeDisabledColor         | color     | 边框不可用状态颜色                                          |
| rv_strokePressColor            | color     | 边框按压颜色                                               |
| rv_textPressColor              | color     | 文字按压颜色                                               |
| rv_textDisabledColor           | color     | 文字不可用颜色                                             |
| rv_isRadiusHalfHeight          | boolean   | 圆角弧度是高度一半                                          |
| rv_isWidthHeightEqual          | boolean   | 圆角矩形宽高相等,取较宽高中大值                               |
| rv_cornerRadius_TL             | dimension | 圆角弧度,单位dp,TopLeft                                    |
| rv_cornerRadius_TR             | dimension | 圆角弧度,单位dp,TopRight                                   |
| rv_cornerRadius_BL             | dimension | 圆角弧度,单位dp,BottomLeft                                 |
| rv_cornerRadius_BR             | dimension | 圆角弧度,单位dp,BottomRight                                |
| rv_isRippleEnable              | boolean   | 是否有Ripple效果,api21+有效                                |
| rv_shadowDx                    | dimension | 阴影-水平x偏移                                             |                            
| rv_shadowDx                    | dimension | 阴影-水平y偏移                                             |
| rv_shadowRadius                | dimension | 阴影-半径                                                 |
| rv_shadowColor                 | color     | 阴影-颜色                                                 |


