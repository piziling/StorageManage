package com.whieenz.storagemanage.utls;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heziwen on 2017/3/10.
 */

public class ListEditorAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Map<String, Object>> mData;// 存储的EditText值
    public Map<String, String> editorValue = new HashMap<String, String>();//

    public ListEditorAdapter(Context context, List<Map<String, Object>> data) {
        mData = data;
        mInflater = LayoutInflater.from(context);
        init();
    }

    // 初始化
    private void init() {
        editorValue.clear();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private Integer index = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        // convertView为null的时候初始化convertView。
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_add_goods, null);
            holder.key = (TextView) convertView
                    .findViewById(R.id.tv_goods_key);
            holder.value = (EditText) convertView
                    .findViewById(R.id.et_goods_value);
            holder.value.setTag(position);
            //holder.userkey = (TextView) convertView.findViewById(R.id.user_key);
            holder.value.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        index = (Integer) v.getTag();
                    }
                    return false;
                }
            });
            class MyTextWatcher implements TextWatcher {
                public MyTextWatcher(ViewHolder holder) {
                    mHolder = holder;
                }

                private ViewHolder mHolder;

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null && !"".equals(s.toString())) {
                        int position = (Integer) mHolder.value.getTag();
                        mData.get(position).put("list_item_inputvalue",
                                s.toString());// 当EditText数据发生改变的时候存到data变量中
                    }
                }
            }
            holder.value.addTextChangedListener(new MyTextWatcher(holder));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.value.setTag(position);
        }
//        Object value = mData.get(position).get("list_item_name");
//        if (value != null) {
//            holder.name.setText((String) value);
//        }
//        value = mData.get(position).get("list_item_title");
//        if (value != null) {
//            holder.title.setText(value.toString());
//        }
//        value = mData.get(position).get("user_key");
//        if (value != null) {
//            holder.userkey.setText(value.toString());
//        } else {
//            holder.userkey.setText("-1");
//        }
//        value = mData.get(position).get("list_item_inputvalue");
//        if (value != null && !"".equals(value)) {
//            holder.value.setText(value.toString());
//        } else {
//            String key = mData.get(position).get("user_key").toString();
//            String inputValue = editorValue.get(key);
//            holder.value.setText(inputValue);
//        }
//        holder.value.clearFocus();
//        if (index != -1 && index == position) {
//            holder.value.requestFocus();
//        }
        return convertView;
    }
}
