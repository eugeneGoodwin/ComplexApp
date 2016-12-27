package com.test.my.complexapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.test.my.complexapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by John on 13.12.2016.
 */
public class ListAdapterDrawer extends ArrayAdapter {

    private String[] mData;

    public ListAdapterDrawer(Context context, String[] objects) {
        super(context, 0);
        mData = objects;
    }

    @Override
    public String getItem(int i) {
        return mData[i];
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(getItem(position));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.menuItem)
        TextView textView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
