package com.feicui.huanxincase.apphx.chat;

import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;

import com.feicui.huanxincase.apphx.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

/**
 * 环信聊天页面
 * Created by Administrator on 2016/10/12 0012.
 */
public class HxChatFragment extends EaseChatFragment {

    public static HxChatFragment getInstance(int chatType,String chatId){
        HxChatFragment chatFragment = new HxChatFragment();
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE,chatType); // 聊天类别(单聊？群聊?)
        args.putString(EaseConstant.EXTRA_USER_ID,chatId); // 对方是谁(和谁聊天)
        chatFragment.setArguments(args);
        return chatFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        customUi();
    }

    private void customUi() {
        /**隐藏EaseUI自定义的标题栏*/
        hideTitleBar();
        /**自定义发送按钮*/
        customSendButton();
    }

    /**发送按钮*/
    @SuppressWarnings("deprecation")
    private void customSendButton() {
        Button btnSend = (Button) inputMenu.findViewById(R.id.btn_send);
        btnSend.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_12));

        btnSend.setBackgroundResource(R.drawable.btn_send_bg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btnSend.setStateListAnimator(null);
        }

        btnSend.setTextColor(getResources().getColorStateList(R.color.selector_light_text));

        ViewGroup.LayoutParams params = btnSend.getLayoutParams();
        params.width = getResources().getDimensionPixelSize(R.dimen.size_48);
        btnSend.setLayoutParams(params);
    }
}
