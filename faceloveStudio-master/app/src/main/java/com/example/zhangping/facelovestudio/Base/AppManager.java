package com.example.zhangping.facelovestudio.Base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by Zhangping on 15/12/28.
 */
public class AppManager {

    private static  AppManager instance ;
    private Stack<Activity> activityStack;
    private AppManager()
    {

    }

    public static  AppManager getAppManager()
    {
        if (instance == null)
        {
            instance = new AppManager();

        }
        return instance;
    }

    public void addActivity(Activity activity)
    {
        if (activityStack == null)
        {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();
        return  activity;
    }

    public void finishActivity(){
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }


    public void finishActivity(Activity activity){
        if (activity != null)
        {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(Class<?> cls){
        for (Activity activity: activityStack
             ) {
            if (activity.getClass().equals(cls))
            {
                finishActivity(activity);
            }

        }
    }

    public void finishAllActivity(){
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i) != null)
            {
                activityStack.get(i).finish();
            }
            activityStack.clear();
        }
    }

    public void AppExit(Context context){
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager)context.getSystemService(
                    context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());
            System.exit(0);

        }catch (Exception e)
        {

        }
    }
}
