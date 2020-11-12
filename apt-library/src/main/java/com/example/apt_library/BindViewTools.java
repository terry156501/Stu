package com.example.apt_library;

import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindViewTools {
    public static void bind(Activity activity){
        Class cla = activity.getClass();
        Class bindViewClass = null;
        try {
            bindViewClass = Class.forName(cla.getName()+"_ViewBinding");
            Method method = bindViewClass.getMethod("bind",cla);
            method.invoke(bindViewClass.getInterfaces(),activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
