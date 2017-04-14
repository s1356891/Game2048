package com.workspace.bin.a2048.app;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by bin on 2017/4/12.
 */

public class MyApplication extends Application {
    public static SharedPreferences mSp;
    public static int mGameGoal;
    public static int mGameLines;
    public static int mItemSize;
    public static int SCORE = 0;
    public static String SP_HIGH_SCORE = "SP_HIGHSCORE";
    public static String KEY_HIGH_SCORE = "KEY_Score";
    public static String KEY_GAME_LINES = "KEY_GAMELINES";
    public static String KEY_GAME_GOAL = "KEY_GameGoal";


    private static MyApplication myApplication;
    public static MyApplication getInstance(){
        return myApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        mSp = getSharedPreferences(SP_HIGH_SCORE, 0);
        mGameLines = mSp.getInt(KEY_GAME_LINES, 4);
        mGameGoal = mSp.getInt(KEY_GAME_GOAL, 2048);
        mItemSize = 0;
    }
}
