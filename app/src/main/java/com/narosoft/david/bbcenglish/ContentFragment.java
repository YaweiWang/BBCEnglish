package com.narosoft.david.bbcenglish;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class ContentFragment extends Fragment {

    private ListView mListView;
    private static ListViewAdapter adapter;
    private static List<BbctitleModel> list = new ArrayList<>();
    private static Handler mHandler;
    private GetDataThread dataThread;
    private static Activity activity;

    public ContentFragment() {
        // Required empty public constructor
    }

    public static ContentFragment newInstance() {
        ContentFragment fragment = new ContentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        mListView = (ListView) view.findViewById(R.id.lv_content);
        adapter = new ListViewAdapter(activity, R.layout.listvew_item);
        mListView.setAdapter(adapter);
        initData();
        initListener();
        return view;
    }

    private void initListener() {

    }

    private void initData(){
        mHandler = new MyHandler(activity);
        dataThread = new GetDataThread();
        dataThread.start();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataThread.stop();
    }

    static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;
        MyHandler(Activity activity) {
            mActivityReference= new WeakReference<Activity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case 1:
                    list = (List<BbctitleModel>) msg.obj;
                    adapter.updateData(list);
                    break;
            }
        }
    }

    static class GetDataThread extends Thread{
        @Override
        public void run() {
            super.run();
            List<BbctitleModel> datas= Net.getBBCData("http://apps.iyuba.com/minutes/titleNewApi.jsp?maxid=0&format=xml&type=android");
            Log.d("data", "run: "+datas.toString());
            System.out.println(datas.toString());
            Message msg = new Message();
            msg.arg1 = 1;
            msg.obj = datas;
            mHandler.sendMessage(msg);
        }
    }

}
