package com.feicui.huanxincase.apphx.conversation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.feicui.huanxincase.apphx.chat.HxChatActivity;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

import java.util.List;

/**
 * 环信会话列表页面。
 * <p/>
 * 此页面无需设置数据，直接调用{@link #refresh()}方法，会刷新显示最新的会话列表数据。
 * 但会话数据发生变更时，此方法不会自动触发，需要主动来调用。
 * <p/>
 * 会话上的未读消息数也是由父类{@link EaseConversationListFragment}自动处理的。
 * <p/>
 * Created by Administrator on 2016/10/12 0012.
 */
public class HxConversationListFragment extends EaseConversationListFragment implements EMMessageListener{

    // EaseContactListFragment
    // EaseConversationListFragment
    // EaseChatFragment

    // 单击联系人,或会话记录
    // 进入ChatActivity
    // 在这个ChatActivity上，显示的是ChatFragment

    private EMChatManager mEMChatManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setConversationListItemClickListener(new EaseConversationListItemClickListener() {
            @Override public void onListItemClicked(EMConversation conversation) {
                HxChatActivity.open(getContext(),conversation.getUserName());
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 自动定制UI
        customUi();
        // 聊天消息监听
        mEMChatManager = EMClient.getInstance().chatManager();
        mEMChatManager.addMessageListener(this);
    }

    private void customUi() {
        hideTitleBar();
    }
    /**start-interface:EMMessageListener---------------------------------------------------*/
    // 接受消息接口,在接受到文本、图片、...,将来调用
    @Override
    public void onMessageReceived(List<EMMessage> list) {
        refresh();
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {
        // 命令消息体，透传消息
    }

    @Override
    public void onMessageReadAckReceived(List<EMMessage> list) {
        // 接收到消息已读的回执（对方已经读了这些消息）
    }

    @Override
    public void onMessageDeliveryAckReceived(List<EMMessage> list) {
        // 收到消息体的发送回执（消息已成功发送到对方设备）
    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }
    /**end-interface:EMMessageListener---------------------------------------------------*/

}
