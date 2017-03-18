package com.whieenz.storagemanage.utls;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.whieenz.storagemanage.R;

import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


/**
 * Created by heziwen on 2017/3/17.
 */

public class SGAdapter extends SimpleAdapter {
    private int[] nums;
    private Context context;

    public SGAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        //Map<Integer,Integer> map = new HashMap<>();
        nums = new int[getCount()];
        for (int i = 0; i < nums.length ; i++) {
            nums[i] = 0;
            Log.e(TAG, "SGAdapter: "+i+"   ==  "+nums[i]);
        }
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view= super.getView(position, convertView, parent);

        final ImageButton deleteBtn=(ImageButton) view.findViewById(R.id.ib_goods_delete);
        ImageButton addBtn=(ImageButton) view.findViewById(R.id.ib_goods_add);
        final TextView textView = (TextView) view.findViewById(R.id.tv_goods_num);
        addBtn.setTag(position);
        deleteBtn.setTag(position);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "SGAdapter:  position   ==  "+v.getTag());
                int tag = (int)v.getTag();
                nums[tag]++;
                Log.e(TAG, "SGAdapter:  nums[tag]++;   ==  "+nums[tag]);
                deleteBtn.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView.setText(""+nums[tag]);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int tag = (int)v.getTag();
                nums[tag]--;
                if (nums[(int)v.getTag()]<=0){
                    deleteBtn.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }else{
                    textView.setText(""+nums[tag]);
                }
            }
        });
        return view;
    }
}
