package com.workspace.bin.a2048;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.workspace.bin.a2048.app.MyApplication;
import com.workspace.bin.a2048.view.GameView;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static GameActivity mGame;
    private TextView tv_goal,tv_score, tv_highScore;
    private Button btn_revert,btn_restart, btn_options;
    private int mHighScore;
    private int mGoal;
    private GameView gameView;

    public GameActivity() {
        mGame = this;
    }

    /**
     * 获取当前Activity的引用
     *
     * @return Activity.this
     */
    public static GameActivity getGameActivity() {
        return mGame;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = (GameView) findViewById(R.id.game_view);
        tv_goal= (TextView) findViewById(R.id.tv_Goal);
        tv_score = (TextView) findViewById(R.id.scroe);
        tv_highScore = (TextView) findViewById(R.id.record);
        btn_revert = (Button) findViewById(R.id.btn_revert);
        btn_restart = (Button) findViewById(R.id.btn_restart);
        btn_options = (Button) findViewById(R.id.btn_option);
        btn_revert.setOnClickListener(this);
        btn_restart.setOnClickListener(this);
        btn_options.setOnClickListener(this);
        initView();
    }

    private void initView() {
        mHighScore = MyApplication.mSp.getInt(MyApplication.KEY_HIGH_SCORE, 0);
        mGoal = MyApplication.mSp.getInt(MyApplication.KEY_GAME_GOAL, 2048);
        tv_highScore.setText("" + mHighScore);
        tv_goal.setText("" + mGoal);

        tv_score.setText("0");
        setScore(0,0);
    }


    public  void setScore(int score, int flag) {
        switch (flag) {
            case 0:
                tv_score.setText(""+score);
                break;
            case 1:
                tv_highScore.setText(""+score);
                break;
            default:
                break;
        }
    }

    public void setGoal(int i) {
        tv_goal.setText(String.valueOf(i));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_revert:
                gameView.revertGame();
                break;
            case R.id.btn_restart:
                gameView.startGame();
                setScore(0,0);
                break;
            case R.id.btn_option:
                finish();
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
