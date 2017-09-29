package com.dm.ycm.yassitant.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dm.ycm.yassitant.R;

/**
 * Created by ycm on 2017/7/15.
 * Description:
 * Modified by:
 */

public class AddMentionAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{
    private String[] mentionData;
    private Context context;

    public String[] getMentionData() {
        return mentionData;
    }

    public void setMentionData(String[] mentionData) {
        this.mentionData = mentionData;
    }

    public AddMentionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mentionData == null ? 0 : mentionData.length;
    }

    @Override
    public Object getItem(int i) {
        return mentionData == null ? null : mentionData[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        HolderView holderView;
        if (convertView == null) {
            convertView = LinearLayout.inflate(context, R.layout.mention_item, null);
            if (convertView != null) {
                holderView = new HolderView(convertView);
                holderView.setContent(mentionData[i]);
                convertView.setTag(holderView);
            }
        }
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private class HolderView {
        private TextView mention;

        HolderView(View view) {
            if (view != null) {
                mention = view.findViewById(R.id.mention_tv);
            }
        }

        public void setContent(String content) {
            mention.setText(content);
        }
    }
}
