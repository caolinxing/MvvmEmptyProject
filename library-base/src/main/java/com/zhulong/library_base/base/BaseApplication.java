package com.zhulong.library_base.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.zhulong.library_base.utils.Utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BaseApplication extends Application
{

    private static boolean sDebug;
    private static BaseApplication sInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Utils.init(this);
        setApplication(this);
    }
    
    /** 当宿主没有继承自该Application时,可以使用set方法进行初始化baseApplication */
    private void setApplication(@NonNull BaseApplication application)
    {
        sInstance = application;

        application
            .registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks()
            {
                @Override
                public void onActivityCreated(@NonNull Activity activity,
                    @Nullable Bundle savedInstanceState)
                {
                    AppManager.getInstance().addActivity(activity);
                }
                
                @Override
                public void onActivityStarted(@NonNull Activity activity)
                {
                    
                }
                
                @Override
                public void onActivityResumed(@NonNull Activity activity)
                {
                    
                }
                
                @Override
                public void onActivityPaused(@NonNull Activity activity)
                {
                    
                }
                
                @Override
                public void onActivityStopped(@NonNull Activity activity)
                {
                    
                }
                
                @Override
                public void onActivitySaveInstanceState(
                    @NonNull Activity activity, @NonNull Bundle outState)
                {
                    
                }
                
                @Override
                public void onActivityDestroyed(@NonNull Activity activity)
                {
                    AppManager.getInstance().removeActivity(activity);
                }
            });
        
    }
    
    /**
     * 获得当前app运行的Application
     */
    public static BaseApplication getInstance()
    {
        if (sInstance == null)
        {
            throw new NullPointerException(
                "please inherit BaseApplication or call setApplication.");
        }
        return sInstance;
    }
    
    public void setsDebug(boolean isDebug)
    {
        sDebug = isDebug;
    }

    public boolean issDebug(){
        return sDebug;
    }

    /**
     * 获取进程名
     *
     * @param context
     * @return
     */
    public static String getCurrentProcessName(Context context)
    {
        ActivityManager am =
            (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps =
            am.getRunningAppProcesses();
        if (runningApps == null)
        {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps)
        {
            if (proInfo.pid == android.os.Process.myPid())
            {
                if (proInfo.processName != null)
                {
                    return proInfo.processName;
                }
            }
        }
        return null;
    }
}
