package com.workspace.bin.a2048;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.workspace.bin.a2048.app.MyApplication;

public class MainActivity extends Activity implements View.OnClickListener {
    private LinearLayout linearLayout;
    private Button btn_goal, btn_line;
    private Button btn_exit, btn_done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        btn_goal = (Button) findViewById(R.id.btn_goal);
        btn_line = (Button) findViewById(R.id.btn_line);
        btn_done = (Button) findViewById(R.id.btn_done);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_goal.setText(""+MyApplication.mSp.getInt(MyApplication.KEY_GAME_GOAL, 2048));
        btn_line.setText(""+MyApplication.mSp.getInt(MyApplication.KEY_GAME_LINES, 4));
        btn_line.setOnClickListener(this);
        btn_goal.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_done.setOnClickListener(this);
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linearLayout.setFocusable(true);
                linearLayout.setFocusableInTouchMode(true);
                linearLayout.requestFocus();
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_goal:
                switch (btn_goal.getText().toString()) {
                    case "2048":
                        btn_goal.setText("4096");
                        break;
                    case "4096":
                         btn_goal.setText("8192");
                        break;
                    case "8192":
                        btn_goal.setText("2048");
                        break;
                }
                SharedPreferences.Editor editor = MyApplication.mSp.edit();
                editor.putInt(MyApplication.KEY_GAME_GOAL,  Integer.parseInt(btn_goal.getText().toString()));
                editor.apply();
                break;
            case R.id.btn_line:
                switch (btn_line.getText().toString()) {
                    case "4":
                        btn_line.setText("5");
                        break;
                    case "5":
                        btn_line.setText("6");
                        break;
                    case "6":
                        btn_line.setText("4");
                        break;
                }
                SharedPreferences.Editor editor1= MyApplication.mSp.edit();
                editor1.putInt(MyApplication.KEY_GAME_LINES,  Integer.parseInt(btn_line.getText().toString()));
                editor1.apply();
                break;
            case R.id.btn_exit:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("确定要退出 2048 吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.btn_done:
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                break;
        }
    }
}
