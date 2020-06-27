package com.example;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.bridge.ReadableNativeMap;
import com.github.wuxudong.rncharts.MPAndroidChartPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
            new SafeAreaContextPackage(),
            new RNScreensPackage(),
            new RNGestureHandlerPackage(),
                    new MPAndroidChartPackage()
            );
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);

        // call for react native >= 0.54.0
        // ReadableNativeArray.setUseNativeAccessor(true);
        // ReadableNativeMap.setUseNativeAccessor(true);
        try {
            Method arrayUseNativeAccessor = ReadableNativeArray.class.getDeclaredMethod("setUseNativeAccessor", boolean.class);
            if (arrayUseNativeAccessor != null) {
                arrayUseNativeAccessor.invoke(null, true);
            }

            Method mapUseNativeAccessor = ReadableNativeMap.class.getDeclaredMethod("setUseNativeAccessor", boolean.class);
            if (mapUseNativeAccessor != null) {
                mapUseNativeAccessor.invoke(null, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
