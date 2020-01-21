package com.digbang.rnconfig;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RNConfigModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private final Class buildConfig;

    public RNConfigModule(ReactApplicationContext reactContext, Class buildConfig) {
        super(reactContext);

        this.reactContext = reactContext;
        this.buildConfig = buildConfig;
    }

    @Override
    public String getName() {
        return "RNConfig";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();

        Field[] fields = this.buildConfig.getDeclaredFields();

        for (Field field : fields) {
            try {
                constants.put(field.getName(), field.get(null));
            } catch (IllegalAccessException e) {
                Log.d(this.getName(), "Cannot get BuildConfig field " + field.getName());
            }
        }

        return constants;
    }
}
