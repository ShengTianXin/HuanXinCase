package com.feicui.huanxincase.apphx.contact.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.feicui.huanxincase.apphx.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
public class AddContactActivity extends AppCompatActivity {
    private Button btnAdd;
    private EditText etUsername;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        btnAdd = (Button) findViewById(R.id.btn_add);
        etUsername = (EditText) findViewById(R.id.et_username);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                addContact(username);
            }
        });
    }

    public void addContact(final String username) {
        showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().addContact(username, "加个好友呗!");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideLoading();
                            navigate();
                        }
                    });
                } catch (final HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideLoading();
                            showError(e.getDescription());
                            clear();
                        }
                    });
                }
            }
        }).start();
    }

    private void showLoading() {
        btnAdd.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        btnAdd.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    private void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void navigate() {
        finish();
    }

    private void clear() {
        etUsername.setText("");
    }

}
