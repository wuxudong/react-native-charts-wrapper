package com.github.wuxudong.rncharts.utils;

import com.github.mikephil.charting.animation.Easing;

import static com.github.mikephil.charting.animation.Easing.EaseInBack;
import static com.github.mikephil.charting.animation.Easing.EaseInBounce;
import static com.github.mikephil.charting.animation.Easing.EaseInCirc;
import static com.github.mikephil.charting.animation.Easing.EaseInCubic;
import static com.github.mikephil.charting.animation.Easing.EaseInElastic;
import static com.github.mikephil.charting.animation.Easing.EaseInExpo;
import static com.github.mikephil.charting.animation.Easing.EaseInOutBack;
import static com.github.mikephil.charting.animation.Easing.EaseInOutBounce;
import static com.github.mikephil.charting.animation.Easing.EaseInOutCirc;
import static com.github.mikephil.charting.animation.Easing.EaseInOutCubic;
import static com.github.mikephil.charting.animation.Easing.EaseInOutElastic;
import static com.github.mikephil.charting.animation.Easing.EaseInOutExpo;
import static com.github.mikephil.charting.animation.Easing.EaseInOutQuad;
import static com.github.mikephil.charting.animation.Easing.EaseInOutQuart;
import static com.github.mikephil.charting.animation.Easing.EaseInOutSine;
import static com.github.mikephil.charting.animation.Easing.EaseInQuad;
import static com.github.mikephil.charting.animation.Easing.EaseInQuart;
import static com.github.mikephil.charting.animation.Easing.EaseInSine;
import static com.github.mikephil.charting.animation.Easing.EaseOutBack;
import static com.github.mikephil.charting.animation.Easing.EaseOutBounce;
import static com.github.mikephil.charting.animation.Easing.EaseOutCirc;
import static com.github.mikephil.charting.animation.Easing.EaseOutCubic;
import static com.github.mikephil.charting.animation.Easing.EaseOutElastic;
import static com.github.mikephil.charting.animation.Easing.EaseOutExpo;
import static com.github.mikephil.charting.animation.Easing.EaseOutQuad;
import static com.github.mikephil.charting.animation.Easing.EaseOutQuart;
import static com.github.mikephil.charting.animation.Easing.EaseOutSine;
import static com.github.mikephil.charting.animation.Easing.Linear;


public class EasingFunctionHelper {

    public static Easing.EasingFunction getEasingFunction(String name) {
        switch (name) {
            case "Linear":
                return Linear;

            case "EaseInQuad":
                return EaseInQuad;

            case "EaseOutQuad":
                return EaseOutQuad;

            case "EaseInOutQuad":
                return EaseInOutQuad;

            case "EaseInCubic":
                return EaseInCubic;

            case "EaseOutCubic":
                return EaseOutCubic;

            case "EaseInOutCubic":
                return EaseInOutCubic;

            case "EaseInQuart":
                return EaseInQuart;

            case "EaseOutQuart":
                return EaseOutQuart;

            case "EaseInOutQuart":
                return EaseInOutQuart;

            case "EaseInSine":
                return EaseInSine;

            case "EaseOutSine":
                return EaseOutSine;

            case "EaseInOutSine":
                return EaseInOutSine;

            case "EaseInExpo":
                return EaseInExpo;

            case "EaseOutExpo":
                return EaseOutExpo;

            case "EaseInOutExpo":
                return EaseInOutExpo;

            case "EaseInCirc":
                return EaseInCirc;

            case "EaseOutCirc":
                return EaseOutCirc;

            case "EaseInOutCirc":
                return EaseInOutCirc;

            case "EaseInElastic":
                return EaseInElastic;

            case "EaseOutElastic":
                return EaseOutElastic;

            case "EaseInOutElastic":
                return EaseInOutElastic;

            case "EaseInBack":
                return EaseInBack;

            case "EaseOutBack":
                return EaseOutBack;

            case "EaseInOutBack":
                return EaseInOutBack;

            case "EaseInBounce":
                return EaseInBounce;

            case "EaseOutBounce":
                return EaseOutBounce;

            case "EaseInOutBounce":
                return EaseInOutBounce;

            default:
                return Linear;

        }
    }
}
