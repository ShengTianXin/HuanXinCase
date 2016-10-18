package com.feicui.huanxincase.apphx;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * 环信相关基础配置
 * Created by Administrator on 2016/10/11 0011.
 */
public class HxBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**初始化环信sdk和easeui库*/
        initEaseUI();
    }

    private void initEaseUI() {
        EMOptions options = new EMOptions();
        options.setAutoLogin(false);// 关闭自动登录
        EaseUI.getInstance().init(this, options);
        /**关闭环信日志*/
        EMClient.getInstance().setDebugMode(false);
    }
}
