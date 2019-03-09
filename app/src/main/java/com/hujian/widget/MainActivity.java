package com.hujian.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hujian.widget.swipelayout.SwipeListView;
import com.hujian.widget.swipelayout.TestAdapter;

public class MainActivity extends AppCompatActivity {

    SwipeListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_layout);
        listView=findViewById(R.id.listview);
        listView.setAdapter(new TestAdapter(this,listView));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("**", "oonclick");
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("**", "oonLongclick");
                return false;
            }
        });
    }

    public void todo(View view) {
        switch (view.getId()){
            case R.id.open_left:
                listView.openLeftMenu();
                break;
            case R.id.open_right:
                listView.openRightMenu();
                break;
            case R.id.close:
                listView.closeMenu();
                break;
        }
    }
}
