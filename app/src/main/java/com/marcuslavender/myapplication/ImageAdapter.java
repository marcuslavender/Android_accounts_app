package com.marcuslavender.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by marcus.lavender on 20/08/2018.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    //private Map<Integer,String> posImage = new HashMap<>();

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public ImageAdapter() {
       // this.posImage.put(1, "piggybanknew");
        //this.posImage.put(2, "paymenthistory");
        //this.posImage.put(3, "deposit");
       // this.posImage.put(4, "pound");
    }

    // references to our images

    private Integer[] mThumbIds = {

            R.drawable.piggybanknew, R.drawable.paymenthistory,
            R.drawable.deposit, R.drawable.pound,
    };


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(400, 600));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 200, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }


}

