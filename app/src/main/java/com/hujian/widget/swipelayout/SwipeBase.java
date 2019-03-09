package com.hujian.widget.swipelayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public abstract class SwipeBase extends FrameLayout {

    private float downY;
    private float downX;

    protected View LeftMenu;
    protected View RightMenu;
    protected View MainContent;

    protected int height;
    protected int leftMenuWight;
    protected int rightMenuWight;
    protected int mainContentWight;
    public SwipeBase(@NonNull Context context) {
        this(context,null);
    }

    public SwipeBase( @NonNull Context context,  @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SwipeBase( @NonNull Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        bindView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height=MainContent.getHeight();
        leftMenuWight=LeftMenu.getWidth();
        rightMenuWight=RightMenu.getWidth();
        mainContentWight=MainContent.getWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        LeftMenu.layout(0-LeftMenu.getWidth(),0,0,MainContent.getHeight());
        MainContent.layout(0,0,MainContent.getWidth(),MainContent.getHeight());
        RightMenu.layout(MainContent.getWidth(),0,MainContent.getWidth()+RightMenu.getWidth(),MainContent.getHeight());

    }

    protected abstract void bindView();

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = ev.getRawX();
                downY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX= (int) ev.getRawX();
                int moveY= (int) ev.getRawY();
                if (Math.abs(downX-moveX)>Math.abs(downY-moveY)){
                    //左右滑动拦截事件
                    return  true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return false;
    }


}
