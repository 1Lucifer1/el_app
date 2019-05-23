package com.example.user.el;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class creatorList extends Activity {
    private RelativeLayout layout;
    private List<creator> mData = null;
    private Context mContext;
    private creatorAdapter mAdapter = null;
    private ListView creator_list_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator_list);
        layout=(RelativeLayout)findViewById(R.id.activity_creator_list);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });

        mContext = creatorList.this;
        creator_list_content = (ListView) findViewById(R.id.creator_list_content);
        mData = new LinkedList<creator>();
        mData.add(new creator("lyl","UI"));
        mData.add(new creator("lzh","UI"));
        mData.add(new creator("lzc","network"));
        mData.add(new creator("fxc","back"));
        mAdapter = new creatorAdapter((LinkedList<creator>) mData, mContext);
        creator_list_content.setAdapter(mAdapter);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.finish();
        return true;
    }
}
