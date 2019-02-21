package com.sun.floatwindow.basefloat;

import android.content.Context;
import android.view.View;

import com.sun.floatwindow.R;

/**
 * @author sun on 2018/12/27.
 */
public class InputWindow extends AbsFloatBase {
    public InputWindow(Context context) {
        super(context);
    }

    @Override
    public void create() {
        super.create();

        mViewMode = WRAP_CONTENT_TOUCHABLE;

        inflate(R.layout.main_layout_input_window);

        requestFocus(true);

        findView(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
            }
        });
    }

    @Override
    protected void onAddWindowFailed(Exception e) {

    }
}
