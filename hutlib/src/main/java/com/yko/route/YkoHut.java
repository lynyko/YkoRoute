package com.yko.route;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by yko on 2017/9/19.
 */

public class YkoHut {
    private static final String PACKAGE_NAME = "com.yko.route";  // 包名
    private static final String YKO_MAP = "YkoMap";              // 类名
    private static Map<String, Class> map = new HashMap<>();

    private static YkoHut INSTANCE = new YkoHut();
    private YkoHut(){

    }

    public static YkoHut getInstance(){
        return INSTANCE;
    }

    public void init(Context context){
        List<String> classNameList = getClassName(context, PACKAGE_NAME);
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

    private List<String > getClassName(Context context, String packageName){
        List<String> classNameList=new ArrayList<String >();
        try {

            DexFile df = new DexFile(context.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = (String) enumeration.nextElement();
                if (className.startsWith(packageName) && className.endsWith(YKO_MAP)) {
                    classNameList.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  classNameList;
    }

    public boolean startActivity(Context context, String path, Bundle bundle){
        if(context == null){
            return false;
        }
        Class cls = getClass(path);
        if(cls == null){
            return false;
        }
        if(!AppCompatActivity.class.isAssignableFrom(cls)
                && !Activity.class.isAssignableFrom(cls)){
            return false;
        }
        Intent intent = new Intent(context, cls);
        if(bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        return true;
    }

    public boolean startService(Context context, String path, Bundle bundle){
        if(context == null){
            return false;
        }
        Class cls = getClass(path);
        if(cls == null){
            return false;
        }
        if(!Service.class.isAssignableFrom(cls)){
            return false;
        }
        Intent intent = new Intent(context, cls);
        if(bundle != null) {
            intent.putExtras(bundle);
        }
        context.startService(intent);
        return true;
    }

    public boolean startActivityForResult(Activity activity, String path, Bundle bundle, int requestCode){
        if(activity == null){
            return false;
        }
        Class cls = getClass(path);
        if(cls == null){
            return false;
        }
        if(!AppCompatActivity.class.isAssignableFrom(cls)
            && !Activity.class.isAssignableFrom(cls)){
            return false;
        }
        Intent intent = new Intent(activity, cls);
        if(bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
        return true;
    }

    public Fragment getFragment(String path){
        Class cls = getClass(path);
        if(cls == null){
            return null;
        }

        if(Fragment.class.isAssignableFrom(cls)){
            try {
                return (Fragment)cls.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public android.support.v4.app.Fragment getFragmentV4(String path){
        Class cls = getClass(path);
        if(cls == null){
            return null;
        }

        if(android.support.v4.app.Fragment.class.isAssignableFrom(cls)){
            try {
                return (android.support.v4.app.Fragment)cls.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public boolean startController(Object from, String path, Map<String, Object> params, Controller.ResultCallback callback){
        Class cls = getClass(path);
        if(cls == null){
            return false;
        }
        if(!Controller.class.isAssignableFrom(cls)){
            return false;
        }
        try {
            Controller controller = (Controller)cls.newInstance();
            controller.start(from, params, callback);
            return true;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Class getClass(String path){
        return map.get(path);
    }
}
