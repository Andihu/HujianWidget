package com.hujian.widget.swipelayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hujian.widget.R;

public class TestAdapter extends BaseAdapter {
    Context context;
    SwipeListView swipeListView;
    public TestAdapter(Context context , SwipeListView listView) {
    this.context=context;
    swipeListView=listView;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final SwipeLaout swipeLaout;
        if (view==null){
            swipeLaout= (SwipeLaout) LayoutInflater.from(context).inflate(R.layout.activity_main,viewGroup,false);
            view=swipeLaout;
        }else {
            swipeLaout= (SwipeLaout) view;
        }
        swipeLaout.setListener(new SwipeLaout.MenuVisiableListener() {
            @Override
            public void opening() {
                swipeListView.setSwipeLaout(swipeLaout);
            }

            @Override
            public void closing() {
            }
        });


        return swipeLaout;
    }


}
