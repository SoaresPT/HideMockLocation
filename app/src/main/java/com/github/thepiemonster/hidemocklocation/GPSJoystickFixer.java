package com.github.thepiemonster.hidemocklocation;

import static com.github.thepiemonster.hidemocklocation.Common.loadClassIfExist;

import android.location.LocationManager;

import java.lang.reflect.Method;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class GPSJoystickFixer {

    static boolean isJoystickApp(XC_LoadPackage.LoadPackageParam lpparam) {
        String packageName = lpparam.packageName;
        Class<?> joystick_MapOverlayService = loadClassIfExist(lpparam, packageName + ".service.MapOverlayService");
        Class<?> joystick_OverlayService = loadClassIfExist(lpparam, packageName + ".service.OverlayService");
        return joystick_MapOverlayService != null && joystick_OverlayService != null;
    }

    static boolean fixTestProviderUpdates(XC_LoadPackage.LoadPackageParam lpparam) {
        String packageName = lpparam.packageName;
        if (!isJoystickApp(lpparam))
            return false;
        Class<?> joystick_MockLocationManager = loadClassIfExist(lpparam, packageName + ".b.u");
        if (joystick_MockLocationManager != null) {
            Method updateLocationMethod = XposedHelpers.findMethodExactIfExists(joystick_MockLocationManager,
                    "a",
                    double.class, // d
                    double.class, // d2
                    double.class, // d3
                    float.class, // f
                    boolean.class, // z
                    float.class, // f2
                    float.class, // f3
                    boolean.class // z2

            );
            Method addTestProviderMethod = XposedHelpers.findMethodExactIfExists(joystick_MockLocationManager,
                    "b"
            );

            if (addTestProviderMethod != null) {
                XposedBridge.hookMethod(addTestProviderMethod, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);

                        @SuppressWarnings("unchecked")
                        List<String> providers = (List<String>) XposedHelpers.getObjectField(param.thisObject, "j");
                        providers.clear();
                        providers.add(LocationManager.GPS_PROVIDER);
                        providers.add(LocationManager.NETWORK_PROVIDER);
                    }
                });
            }
            if (updateLocationMethod != null) {
                XposedBridge.hookMethod(updateLocationMethod, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        Object o = param.thisObject;

                        LocationManager locationManager = (LocationManager) XposedHelpers.getObjectField(o, "d");
                        @SuppressWarnings("unchecked")
                        List<String> providers = (List<String>) XposedHelpers.getObjectField(o, "j");

                        try {
                            for (String next : providers) {
                                if (!locationManager.isProviderEnabled(next)) {
                                    locationManager.setTestProviderEnabled(next, true);
                                }

                            }
                        } catch (Exception e) {
                            // removeProviders
                            XposedHelpers.callMethod(o, "c");
                            // addProviders
                            XposedHelpers.callMethod(o, "b");
                        }
                    }
                });
                return true;
            }
        }
        return false;
    }

    static boolean tryFixJoystickApp(XC_LoadPackage.LoadPackageParam lpparam) {
        return fixTestProviderUpdates(lpparam);
    }
}
