package com.tj.pxdl.carlease.widget;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.LinkedList;

/**
 * Created by Chaersi on 17/3/14.
 */
public class ActivityManagerCallBack implements Application.ActivityLifecycleCallbacks {

    public static LinkedList<Activity> mActivityLinkedList=new LinkedList<>();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (null!=mActivityLinkedList&&null!=activity) {
            mActivityLinkedList.addFirst(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (null!=mActivityLinkedList&&null!=activity) {
            if (mActivityLinkedList.contains(activity)) {
                mActivityLinkedList.remove(activity);
            }
        }
    }
}
