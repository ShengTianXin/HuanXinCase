package com.feicui.huanxincase.user;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.feicui.huanxincase.HomeActivity;
import com.feicui.huanxincase.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 登录页面
 * Created by Administrator on 2016/10/11 0011.
 */
public class LoginFragment extends DialogFragment {

    private Unbinder mUnbinder;

    @BindView(R.id.edit_username)
    TextInputEditText etUsername;
    @BindView(R.id.edit_password)
    TextInputEditText etPassword;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.button_confirm)
    Button btnConfirm;

    // 用来create视图
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 设置一下对话框是无标题模式
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    // 用来初始设置相关视图
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    @OnClick(R.id.button_confirm)
    public void login() {
        /**trim()指去除首尾的空格*/
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        handleLogin(username, password);
    }

    private void handleLogin(final String username,final String password){
        startLoading();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            String info = getString(R.string.user_error_not_null);
            stopLoading();
            showLoginFail(info);
            return;
        }

        EMClient.getInstance().login(username, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stopLoading();
                        navigateToHome();
                    }
                });
            }

            @Override
            public void onError(int i, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stopLoading();
                        showLoginFail(s);
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    /**视图实现 start ------------------------------------------------------*/
    public void startLoading(){
        btnConfirm.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        setCancelable(false);

    }

    public void stopLoading(){
        btnConfirm.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        setCancelable(true);
    }

    public void showLoginFail(String msg){
        String info = getString(R.string.user_error_register_fail,msg);
        Toast.makeText(getContext(),info, Toast.LENGTH_SHORT).show();
    }

    public void navigateToHome() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
    /**视图结束 end ---------------------------------------------------------*/

}
