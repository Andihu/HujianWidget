package com.hujian.widget.swipelayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import com.hujian.widget.R;

public class SwipeLaout extends SwipeBase {

    private int downX;
    private int downY;
    private Scroller mScroller;
    private int center;
    private static final int TO_OPEN_CENTER = 0;
    private static final int TO_OPEN_LEFT = 1;
    private static final int TO_OPEN_RIGHT = 2;

    private static int TEMP_STATUS = TO_OPEN_CENTER;

    public MenuVisiableListener getListener() {
        return listener;
    }

    public void setListener(MenuVisiableListener listener) {
        this.listener = listener;
    }

    private MenuVisiableListener listener;

    public SwipeLaout(@NonNull Context context) {
        this(context, null);
    }

    public SwipeLaout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLaout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context, new DecelerateInterpolator());

    }

    @Override
    protected void bindView() {
        MainContent = findViewById(R.id.mian);
        LeftMenu = findViewById(R.id.left);
        RightMenu = findViewById(R.id.right);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getRawX();
                downY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getRawX();
                int moveY = (int) event.getRawY();
                if (Math.abs(downX - moveX) > Math.abs(downY - moveY)) {
                    requestDisallowInterceptTouchEvent(true);
                }
                int disX = downX - moveX;
                int scrollx = getScrollX();
                int startX = scrollx + disX;

                //      Log.e("###", "onTouchEvent: " + getScrollX());
                if (startX < -LeftMenu.getWidth()) {// 设置左边界
                    scrollTo(-LeftMenu.getWidth(), 0);
                } else if (startX > RightMenu.getWidth()) {// 设置右边界
                    scrollTo(RightMenu.getWidth(), 0);
                } else {// 如果不超过边界值，就要根据屏幕左上角的点的X 值，来计算位置
                    scrollBy(disX, 0);
                }
                downX = moveX;
                downY = moveY;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                //todo 是否滑动到原点
                center = LeftMenu.getWidth() / 2;
                int scrollX = getScrollX();
                Log.e("**", "onTouchEvent: " + scrollX + "   cneter:" + center);

                if (scrollX < -center) {
                    //todo 张开左菜单
                    TEMP_STATUS = TO_OPEN_LEFT;
                    listener.opening();
                } else if (scrollX > center) {
                    //todo 张开右菜单
                    TEMP_STATUS = TO_OPEN_RIGHT;
                    listener.opening();
                } else {
                    //TODO 关闭菜单
                    TEMP_STATUS = TO_OPEN_CENTER;
                    listener.closing();
                }
                resetMenu();
                break;
        }
        return true;
    }

    private void resetMenu() {
        int scrollX = getScrollX();
        switch (TEMP_STATUS) {
            case TO_OPEN_CENTER:
                mScroller.startScroll(scrollX, 0, -scrollX, 0, 200);
                break;
            case TO_OPEN_LEFT:
                mScroller.startScroll(scrollX, 0, -LeftMenu.getWidth() - scrollX, 0, 200);
                break;
            case TO_OPEN_RIGHT:
                mScroller.startScroll(scrollX, 0, LeftMenu.getWidth() - scrollX, 0, 200);
                break;
        }

        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int currX = mScroller.getCurrX();
            scrollTo(currX, 0);
            invalidate();
        }
    }

    public void openLeftMenu() {

        if (TEMP_STATUS == TO_OPEN_LEFT)
            return;
        TEMP_STATUS = TO_OPEN_LEFT;
        resetMenu();
    }

    public void openReftMenu() {
        if (TEMP_STATUS == TO_OPEN_RIGHT)
            return;
        TEMP_STATUS = TO_OPEN_RIGHT;
        resetMenu();
    }

    public void close() {

        if (TEMP_STATUS == TO_OPEN_CENTER)
            return;
        TEMP_STATUS = TO_OPEN_CENTER;
        resetMenu();
    }
    interface MenuVisiableListener{
        void opening();
        void closing();
    }
}
