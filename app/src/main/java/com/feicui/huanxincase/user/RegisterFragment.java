package com.feicui.huanxincase.user;


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

import com.feicui.huanxincase.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注册页面
 * Created by Administrator on 2016/10/11 0011.
 */
public class RegisterFragment extends DialogFragment {

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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
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
    public void register() {
        /**trim()指去除首尾的空格*/
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        handleRegister(username,password);
    }

    public void handleRegister(final String username,final String password){
        /**开始loading视图*/
        startLoading();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            stopLoading();
            String info = getString(R.string.user_error_not_null);
            showRegisterFail(info);
            return;
        }

        /**执行注册链接*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(username,password);
                    /**未出现异常，表现成功, 开始做一些视图上的工作*/
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stopLoading();
                            showRegisterSuccess();
                            dismiss();
                        }
                    });
                } catch (final HyphenateException e) {
                    /**出现异常，表现失败, 开始做一些视图上的工作*/
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stopLoading();
                            showRegisterFail(e.getDescription());
                        }
                    });
                }
            }
        }).start();
    }
    /**视图实现 start ------------------------------------------------------*/
    public void startLoading(){
        setCancelable(false);
        btnConfirm.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void stopLoading(){
        setCancelable(true);
        btnConfirm.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    public void showRegisterSuccess(){
        Toast.makeText(getContext(),R.string.user_register_success, Toast.LENGTH_SHORT).show();
    }

    public void showRegisterFail(String msg){
        String info = getString(R.string.user_error_register_fail,msg);
        Toast.makeText(getContext(),info, Toast.LENGTH_SHORT).show();
    }
    /**视图结束 end ---------------------------------------------------------*/

}
