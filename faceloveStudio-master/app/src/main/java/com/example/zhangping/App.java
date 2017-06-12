package com.example.zhangping;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

//import io.rong.imkit.RongContext;
//import io.rong.imkit.RongIM;
//import io.rong.imlib.ipc.RongExceptionHandler;

/**
 * Created by zhangping on 16/2/19.
 */
public class App extends Application {

    public void onCreate() {

        super.onCreate();
        /**
         * 注意：
         *
         * IMKit SDK调用第一步 初始化
         *
         * context上下文
         *
         * 只有两个进程需要初始化，主进程和 push 进程
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

//            RongIM.init(this);

            /**
             * 融云SDK事件监听处理
             *
             * 注册相关代码，只需要在主进程里做。
             */
//            if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
//
//                RongCloudEvent.init(this);
//                DemoContext.init(this);
//
//                Thread.setDefaultUncaughtExceptionHandler(new RongExceptionHandler(this));
//
//                try {
//                    RongIM.registerMessageType(AgreedFriendRequestMessage.class);
//
//                    RongIM.registerMessageTemplate(new ContactNotificationMessageProvider());
//                    RongIM.registerMessageTemplate(new RealTimeLocationMessageProvider());
//                    //@ 消息模板展示
//                    RongContext.getInstance().registerConversationTemplate(new NewDiscussionConversationProvider());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }


    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
