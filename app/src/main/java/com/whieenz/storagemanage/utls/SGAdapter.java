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

import static android.R.attr.tag;
import static android.content.ContentValues.TAG;


/**
 * Created by heziwen on 2017/3/17.
 */

public class SGAdapter extends SimpleAdapter implements View.OnClickListener{
    private int[] nums;
    private Context context;
    private TextView textView;
    private SGAdapterCallback callback;

    public SGAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to,SGAdapterCallback callback) {
        super(context, data, resource, from, to);
        //Map<Integer,Integer> map = new HashMap<>();
        this.callback = callback;
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
        textView.setTag(position);

        if(nums[position]==0){   //判断是否隐藏按钮
            textView.setVisibility(View.GONE);
            deleteBtn.setVisibility(View.GONE);
        }

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

                callback.click(v);
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

                callback.click(v);
            }
        });
        return view;
    }


    public int[] getNums() {
        return nums;
    }

    public void setNums(int[] nums) {
        this.nums = nums;
    }

    public void cleanAll() {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 0;
        }

    }

    /**
     * 获取选择了几类物资
     */
    public int getSelect(){
        int tag = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>0){
                tag++;
            }
        }
        return tag;
    }

    /**
     * 获取选择物资的总数量
     */
    public int getSelects(){
        int tag = 0;
        for (int i = 0; i < nums.length; i++) {

                tag+= nums[i];

        }
        return tag;
    }

    /**
     * 初始化选择数据  当点击清除按钮时执行
     */
    public void initNums(){
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    @Override
    public void onClick(View view) {

    }

    public interface SGAdapterCallback{
        public void click(View view);
    }
}
