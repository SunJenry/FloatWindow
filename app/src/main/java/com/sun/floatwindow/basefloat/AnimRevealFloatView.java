package com.sun.floatwindow.basefloat;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SizeUtils;
import com.sun.floatwindow.R;

public class AnimRevealFloatView extends AbsFloatBase {


    private final LinearLayout lLRoot;

    public AnimRevealFloatView(Context context) {
        super(context);

        mViewMode = WRAP_CONTENT_TOUCHABLE;
//
        mGravity = Gravity.END | Gravity.CENTER_VERTICAL;

        inflate(R.layout.main_layout_anim_reveal_window);

        final RelativeLayout rLRoot = findView(R.id.rlRoot);
        lLRoot = findView(R.id.lLRoot);

        rLRoot.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams = rLRoot.getLayoutParams();
                layoutParams.width = SizeUtils.dp2px(120);
                rLRoot.setLayoutParams(layoutParams);

                lLRoot.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final ViewGroup.LayoutParams layoutParams = lLRoot.getLayoutParams();

                        ValueAnimator valueAnimator = ValueAnimator.ofInt(SizeUtils.dp2px(36), SizeUtils.dp2px(120));
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                layoutParams.width = (int) valueAnimator.getAnimatedValue();
                                lLRoot.setLayoutParams(layoutParams);
                            }
                        });
                        valueAnimator.setDuration(1000);
                        valueAnimator.start();
                    }
                }, 10);

            }
        });

    }

    @Override
    protected void onAddWindowFailed(Exception e) {
        Toast.makeText(mContext, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
