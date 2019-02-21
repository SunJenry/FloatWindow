package com.sun.floatwindow.basefloat;

import android.content.Context;
import android.view.Gravity;

import com.sun.floatwindow.R;

/**
 * @author sun on 2018/12/26.
 */
public class NotFullScreenTouchDisableFloatWindow extends AbsFloatBase {

    public NotFullScreenTouchDisableFloatWindow(Context context) {
        super(context);
    }

    @Override
    public void create() {
        super.create();

        mViewMode = WRAP_CONTENT_NOT_TOUCHABLE;

        mGravity = Gravity.CENTER_VERTICAL | Gravity.START;

        inflate(R.layout.main_layout_float_not_full_screen_touch_disable);
    }

    @Override
    protected void onAddWindowFailed(Exception e) {

    }
}
