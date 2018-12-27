package com.sun.floatwindow.basefloat;

import android.content.Context;
import android.view.View;

import com.sun.floatwindow.R;

/**
 * @author sun on 2018/12/27.
 */
public class FloatPermissionDetectView extends AbsFloatBase {

    public FloatPermissionDetectView(Context context) {
        super(context);
    }

    @Override
    public void create() {
        super.create();

        mViewMode = WRAP_CONTENT_TOUCHABLE;

        inflate(R.layout.main_layout_float_permission_detect);

        findView(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
            }
        });
    }
}
