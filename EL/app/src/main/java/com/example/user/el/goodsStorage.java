package com.example.user.el;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class goodsStorage extends Activity {
    private RelativeLayout layout;
    private Button leave;
    private List<file> mData = null;
    private storageAdapter mAdapter = null;
    private ListView storageList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_storage);
        layout = (RelativeLayout)findViewById(R.id.activity_goods_storage);
        leave = (Button) findViewById(R.id.storage_leave);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });

        mContext = goodsStorage.this;

        storageList = (ListView) findViewById(R.id.storage_list);
        mData = new LinkedList<file>();
        mData.add(new file("file1","fuck you"));
        mData.add(new file("file2","fuck you"));
        mData.add(new file("file3","fuck you"));
        mAdapter = new storageAdapter((LinkedList<file>) mData, mContext);
        storageList.setAdapter(mAdapter);
    }

    public boolean onTouchEvent(MotionEvent event){
        this.finish();
        return true;
    }

    public void leaveClick(View v){
        this.finish();
    }
}
