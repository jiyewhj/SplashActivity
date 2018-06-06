package com.wh.torch;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;



public class KeyboardChangeListener  implements ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG = "ListenerHandler";
    private View mContentView;
    private int mOriginHeight;
    private int mPreHeight;
    private keyboardListener mKeyboardListener;
    private Activity activity;
    public interface keyboardListener {
        void onKeyboardChange(boolean isShow, int keyboardHeight);
    }
    public void setKeyboardLisener(keyboardListener keyBoardLisener) {
        this.mKeyboardListener = keyBoardLisener;
    }
    public KeyboardChangeListener(Activity activity) {
        if(activity == null) {
            Log.i(TAG, "activity is null");
            return;
        }
        this.activity = activity;
        mContentView = findContentView(activity);
        if(mContentView != null) {
            addContentTreeObserver();
        }
    }
    public KeyboardChangeListener(AppCompatActivity activity) {
        this.activity = activity;
        if(activity == null) {
            Log.i(TAG, "activity is null");
            return;
        }
        mContentView = findContentView(activity);
        if(mContentView != null) {
            addContentTreeObserver();
        }
    }

    private View findContentView(Activity contextObj) {
        return contextObj.findViewById(android.R.id.content);
    }
    private View findContentView(AppCompatActivity contextObj) {
        return contextObj.findViewById(android.R.id.content);
    }
    private void addContentTreeObserver() {
        mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
    //视图树，监听整个布局的改变
    @Override
    public void onGlobalLayout() {
        Rect r = new Rect();
        //获取当前界面可视部分
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        //获取屏幕的高度
        int screenHeight =  activity.getWindow().getDecorView().getRootView().getHeight();
        //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
        int heightDifference = (int) (screenHeight - r.bottom);
        if (mKeyboardListener != null) {
            mKeyboardListener.onKeyboardChange(heightDifference>screenHeight/4, heightDifference);
        }
    }
    public void destroy() {
        if (mContentView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }
}
