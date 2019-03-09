package com.hujian.widget.swipelayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

public class SwipeListView extends ListView implements AbsListView.OnScrollListener {


    private SwipeLaout swipeLaout;

    public SwipeLaout getSwipeLaout() {
        return swipeLaout;
    }

    public void setSwipeLaout(SwipeLaout swipeLaout) {
        this.swipeLaout = swipeLaout;
    }

    public SwipeListView(Context context) {
        this(context, null);
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOnScrollListener(this);
    }


    public void openLeftMenu(){
        swipeLaout.openLeftMenu();

    }
    public void openRightMenu(){
        swipeLaout.openReftMenu();
    }
    public void closeMenu(){
        swipeLaout.close();
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

        if (swipeLaout!=null){
            swipeLaout.close();
        }
    }
}
