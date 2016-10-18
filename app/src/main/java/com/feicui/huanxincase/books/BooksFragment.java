package com.feicui.huanxincase.books;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicui.huanxincase.R;

/**
 * 作者：yuanchao on 2016/10/12 0012 11:15
 * 邮箱：yuanchao@feicuiedu.com
 */

public class BooksFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_books, container, false);
    }
}
