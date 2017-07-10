package com.narosoft.david.bbcenglish;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2017/7/10.
 */

public class ListViewAdapter extends ArrayAdapter<BbctitleModel> {

    private List<BbctitleModel> list = new ArrayList<>();
    private int resourseId ;
    private ImageView image;
    private Context context;
    public ListViewAdapter(Context context, int resource) {
        super(context, resource);
        this.resourseId = resource;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public BbctitleModel getItem(int position) {
        Log.d("data", "getItem: "+list.get(position).toString());
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BbctitleModel data = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourseId, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.iv_pic);
            viewHolder.textViewDetail = (TextView) view.findViewById(R.id.tv_detail);
            viewHolder.textViewTitle = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(viewHolder);
        } else {
            view =convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if(data == null){
            return view;
        }
        setImage(data.getPic(), viewHolder.imageView);
        viewHolder.textViewTitle.setText(data.getTitle_cn());
        viewHolder.textViewDetail.setText(data.getCreatTime().substring(0,10)+" | 阅读："+data.getReadCount());
        return view;
    }

     class ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDetail;
    }

    public void updateData(List<BbctitleModel> list){
        this.list = list;
        if(list!= null){
            notifyDataSetChanged();
        }
    }

    public void setImage(String url, final ImageView imageView){
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest imageRequest = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(imageRequest);
    }

}
