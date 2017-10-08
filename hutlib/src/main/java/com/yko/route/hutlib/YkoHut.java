package com.yko.route.hutlib;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.yko.route.IRoute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

import static com.yko.route.RouteConstant.YKO_MAP;
import static com.yko.route.RouteConstant.PACKAGE_NAME;

/**
 * Created by yko on 2017/9/19.
 */

public class YkoHut {
    public static final String PACKAGE_NAME = "com.yko.route";  // 包名
    public static final String YKO_MAP = "YkoMap";              // 类名
    public static final String ANNOTATION_YKO_ROUTE = PACKAGE_NAME + ".YkoRoute";   // annotation
    private static Map<String, String> map = new HashMap<>();
    public static void init(Context context){
        List<String> classNameList = getClassName(context, PACKAGE_NAME, YKO_MAP);
        for(String className : classNameList) {
            try {
                Class cls = Class.forName(className);
                Object o = cls.newInstance();
                if(o instanceof IRoute) {
                    IRoute route = (IRoute) o;
                    route.routeMap(map);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for (String key : map.keySet()) {
            Log.e("map", "key= "+ key + " and value= " + map.get(key));
        }
    }

    private static List<String > getClassName(Context context, String packageName, String endName){
        List<String> classNameList=new ArrayList<String >();
        try {

            DexFile df = new DexFile(context.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = (String) enumeration.nextElement();
                if (className.startsWith(packageName) && className.endsWith(endName)) {
                    classNameList.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  classNameList;
    }

    public static Class getActivityClass(String path){
        String className = map.get(path);
        if(TextUtils.isEmpty(className)){
            return null;
        }
        try {
            Class cls = Class.forName(className);
            Object o = cls.newInstance();
            if(o instanceof Activity) {
                return cls;
            } else {
                return null;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Fragment getFragment(String path){
        String className = map.get(path);
        if(TextUtils.isEmpty(className)){
            return null;
        }
        try {
            Class cls = Class.forName(className);
            Object o = cls.newInstance();
            if(o instanceof Fragment) {
                return (Fragment) o;
            } else {
                return null;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static android.support.v4.app.Fragment getFragmentV4(String path){
        String className = map.get(path);
        if(TextUtils.isEmpty(className)){
            return null;
        }
        try {
            Class cls = Class.forName(className);
            Object o = cls.newInstance();
            if(o instanceof android.support.v4.app.Fragment) {
                return (android.support.v4.app.Fragment) o;
            } else {
                return null;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
