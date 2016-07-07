package com.gshindi.android.gshindi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textView = (TextView) findViewById(R.id.total_Score);

        String score = getIntent().getExtras().get("totalScore").toString();
        String scoreTestViewString = "Total Score : " + score;
        textView.setText(scoreTestViewString);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(ResultActivity.this, QuestionSetListActivity.class));
        finish();

    }
}
