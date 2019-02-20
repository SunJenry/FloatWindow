package com.sun.floatwindow;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.sun.floatwindow.basefloat.FloatWindowParamManager;
import com.sun.floatwindow.basefloat.RomUtils;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

        findViewById(R.id.btn_check_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean permission = FloatWindowParamManager.checkPermission(getApplicationContext());
                if (permission&&!RomUtils.isVivoRom()) {
                    Toast.makeText(MainActivity.this, R.string.has_float_permission, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
                    intent.setAction(FloatWindowService.ACTION_CHECK_PERMISSION_AND_TRY_ADD);
                    startService(intent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.no_float_permission, Toast.LENGTH_SHORT).show();
                    showOpenPermissionDialog();
                }
            }
        });

        findViewById(R.id.btn_stop_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
                stopService(intent);
            }
        });

        findViewById(R.id.btn_full_screen_touch_able).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
                intent.setAction(FloatWindowService.ACTION_FULL_SCREEN_TOUCH_ABLE);
                startService(intent);
            }
        });

        findViewById(R.id.btn_full_screen_touch_disable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
                intent.setAction(FloatWindowService.ACTION_FULL_SCREEN_TOUCH_DISABLE);
                startService(intent);
            }
        });

        findViewById(R.id.btn_not_full_touch_able).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
                intent.setAction(FloatWindowService.ACTION_NOT_FULL_SCREEN_TOUCH_ABLE);
                startService(intent);
            }
        });

        findViewById(R.id.btn_not_full_touch_disable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
                intent.setAction(FloatWindowService.ACTION_NOT_FULL_SCREEN_TOUCH_DISABLE);
                startService(intent);
            }
        });

        findViewById(R.id.btn_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
                intent.setAction(FloatWindowService.ACTION_INPUT);
                startService(intent);
            }
        });
    }

    private void showOpenPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.no_float_permission);
        builder.setMessage(R.string.go_t0_open_float_ask);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                FloatWindowParamManager.tryJumpToPermissionPage(getApplicationContext());

                Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
                intent.setAction(FloatWindowService.ACTION_CHECK_PERMISSION_AND_TRY_ADD);
                startService(intent);
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
        stopService(intent);
    }
}
