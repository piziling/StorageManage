package com.whieenz.storagemanage.utls;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.whieenz.storagemanage.R;

import java.util.List;
import java.util.Map;


/**
 * Created by heziwen on 2017/3/30.
 */

public class DJAdapter extends SimpleAdapter {

    public DJAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view= super.getView(position, convertView, parent);
        final  TextView djztTv = (TextView) view.findViewById(R.id.tv_item_djzt);
        String djzt = djztTv.getText().toString();
        if (djzt.equals("未通过")){
            //djztTv.setBackground(view.getResources().getDrawable(R.drawable.red_tv_border));
           // djztTv.setBackground(parent.getResources().getDrawable(R.drawable.red_tv_border));
            djztTv.setBackgroundDrawable(view.getResources().getDrawable(R.drawable.red_tv_border));
            djztTv.setTextColor(Color.RED);
        }

        return view;
    }
}
