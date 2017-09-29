package com.dm.ycm.yassitant.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dm.ycm.yassitant.R;
import com.dm.ycm.yassitant.adapter.AddMentionAdapter;

/**
 * Created by ycm on 2017/7/15.
 * Description:
 * Modified by:
 */

public class AddMentionDialog extends Dialog {
    private ListView mentionListView;
    private AddMentionAdapter addMentionAdapter;
    private String[] mentionData;

    public AddMentionDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }

    public AddMentionDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public AddMentionDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_mention_dialog);
        addMentionAdapter = new AddMentionAdapter(getContext());
        mentionData = getContext().getResources().getStringArray(R.array.mention);
        mentionListView = findViewById(R.id.mention_lv);
        addMentionAdapter.setMentionData(mentionData);
        mentionListView.setAdapter(addMentionAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void showDialog() {
        show();
    }

    public void releaseDialog() {
        addMentionAdapter = null;
        cancel();
    }
}
