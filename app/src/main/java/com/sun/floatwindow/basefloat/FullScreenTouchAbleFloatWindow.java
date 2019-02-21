package com.sun.floatwindow.basefloat;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.sun.floatwindow.R;

/**
 * @author sun on 2018/12/26.
 */
public class FullScreenTouchAbleFloatWindow extends AbsFloatBase {

    public FullScreenTouchAbleFloatWindow(Context context) {
        super(context);
    }

    @Override
    public void create() {
        super.create();

        mViewMode = FULLSCREEN_TOUCHABLE;

        inflate(R.layout.main_layout_float_full_screen_touch_able);

        findView(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "remove", Toast.LENGTH_SHORT).show();
                remove();
            }
        });
    }

    @Override
    protected void onAddWindowFailed(Exception e) {

    }
}
