package com.feicui.huanxincase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.feicui.huanxincase.user.LoginFragment;
import com.feicui.huanxincase.user.RegisterFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SplashActivity extends AppCompatActivity {

    // 上面有两个按钮
    // 按下后，分别show出不同的fragment就完事了

    // 1. 简单 (button)
    // 2. 要先准备好两个能show的fragment，分别是.....

    private LoginFragment mLoginFragment;
    private RegisterFragment mRegisterFragment;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @OnClick(R.id.button_login)
    public void showLoginDialog() {
        if (mLoginFragment == null) {
            mLoginFragment = new LoginFragment();
        }
        mLoginFragment.show(getSupportFragmentManager(), null);
    }

    @OnClick(R.id.button_register)
    public void showRegisterDialog() {
        if (mRegisterFragment == null) {
            mRegisterFragment = new RegisterFragment();
        }
        mRegisterFragment.show(getSupportFragmentManager(), null);
    }

}
